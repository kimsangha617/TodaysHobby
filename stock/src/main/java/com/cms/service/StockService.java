package com.cms.service;

import com.cms.domain.Stock;
import com.cms.exception.SkuNotFoundException;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.repository.SkuRepository;
import com.cms.repository.StockJpaRepository;
import com.cms.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ConcurrentModificationException;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockService {

    private final StockJpaRepository stockRepository;
    private final SkuRepository skuRepository;


    @Transactional(readOnly = true)
    public Stock getStock(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("존재하지 않는 " + id + " 입니다."));
    }

//    Stock 재고 추가가 되려면

    @Transactional
    public void addStock(Long skuId, int quantity) {
        Stock stock = skuRepository.findByIdForUpdate(skuId)
                .orElseThrow(() -> new SkuNotFoundException("존재하지 않는 " + skuId + " 입니다"));

        stock.increaseStockQuantity(quantity);
        stockRepository.save(stock);
    }

    @Transactional
    public void decreaseStock(Long skuId, int quantity) {
        try {
            Stock stock = stockRepository.findByIdForUpdate(skuId)
                    .orElseThrow(() -> new SkuNotFoundException("존재하지 않는 skuId: " + skuId + " 입니다."));
            stock.decreaseStockQuantity(quantity);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.error("재고 감소 작업 실패 : id={}, quantity={}", skuId, quantity, e);
            throw new ConcurrentModificationException("재고 수정 충돌 발생 :" + skuId);
        }
    }



}
