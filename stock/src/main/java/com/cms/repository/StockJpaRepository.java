package com.cms.repository;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface StockJpaRepository {

    Optional<StockEntity> findByStockId(String stockId);

}
