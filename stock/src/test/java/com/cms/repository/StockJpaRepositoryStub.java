package com.cms.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class StockJpaRepositoryStub implements StockJpaRepository {

    private final List<StockEntity> stockEntities = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Optional<StockEntity> findBySkuId(String skuId) {
        return Optional.empty();
    }
}
