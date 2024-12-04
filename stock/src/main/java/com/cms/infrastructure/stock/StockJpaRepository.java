package com.cms.infrastructure.stock;


import com.cms.infrastructure.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByStockId(Long stockId);

    Optional<StockEntity> findBySkuId(Long skuId);

    StockEntity save(StockEntity stockEntity);


    Integer decreaseStock(Long stockId, Long quantity);

    void deleteAll();

}
