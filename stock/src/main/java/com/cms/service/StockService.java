package com.cms.service;

import com.cms.domain.Stock;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ConcurrentModificationException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;


    @Transactional(readOnly = true)
    public int checkQuantity(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new StockNotFoundException("존재하지 않는 " + id + " 입니다."));

        return stock.getQuantity();
    }

    @Transactional
    public void decrease(Long id, int quantity) {
        try {
            Stock stock = stockRepository.findByIdWithOptimisticLock(id)
                    .orElseThrow(() -> new StockNotFoundException("존재하지 않는 " + id + " 입니다."));
            stock.decrease(quantity);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ConcurrentModificationException("재고 수정 충돌 발생 :" + id);
        }
    }

    @Transactional
    public void increase(Long id, int quantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("존재하지 않는 " + id + " 입니다."));
        stock.increase(quantity);
    }

}
