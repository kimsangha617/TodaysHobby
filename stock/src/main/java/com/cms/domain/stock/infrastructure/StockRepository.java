package com.cms.domain.stock.infrastructure;

import com.cms.domain.stock.Stock;
import com.cms.infrastructure.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    List<Stock> findAllByProductNumberIn(List<String> productNumbers);

    Optional<Stock> findByStockId(Long stockId);

    Stock save(Stock stock);

    Integer decreaseStock(Long stockId, Long quantity);

    void deleteAll();


}
