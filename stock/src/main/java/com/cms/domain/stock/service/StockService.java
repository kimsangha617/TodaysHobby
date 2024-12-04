package com.cms.domain.stock.service;

import com.cms.domain.stock.Stock;
import com.cms.domain.stock.infrastructure.StockRepository;
import com.cms.infrastructure.stock.entity.StockEntity;
import com.cms.common.exception.InvalidDecreaseQuantityException;
import com.cms.common.exception.SkuNotFoundException;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.infrastructure.sku.SkuJpaRepository;
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

    private final StockRepository stockRepository;


    @Transactional(readOnly = true)
    public Stock getStockOne(Long stockId) {
        return stockRepository.findByStockId(stockId)
                .orElseThrow( () -> new StockNotFoundException("존재하지 않는 stockId: " + stockId + " 입니다"));
    }

//    Stock 재고 추가가 되려면

    @Transactional
    public void addStock(Long stockId, int quantity) {
        if (quantity < 0) {
            throw new InvalidDecreaseQuantityException("유효 하지 않은 수량 입니다");
        }
        Stock stock = stockRepository.findByStockId(stockId)
                .orElseThrow(() -> new StockNotFoundException("존재하지 않는 stockId: " + stockId + " 입니다"))
                ;

        stock.increaseStockQuantity(quantity);
//        stockRepository.save(stock);
    }

    @Transactional
    public void decreaseStock(Long stockId, int quantity) {
        try {
            Stock stock = stockRepository.findByStockId(stockId)
                    .orElseThrow(() -> new SkuNotFoundException("존재하지 않는 stockId: " + stockId + " 입니다."));
            stock.decreaseStockQuantity(quantity);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.error("재고 감소 작업 실패 : id={}, quantity={}", stockId, quantity, e);
            throw new ConcurrentModificationException("재고 수정 충돌 발생 :" + stockId);
        }
    }



}
