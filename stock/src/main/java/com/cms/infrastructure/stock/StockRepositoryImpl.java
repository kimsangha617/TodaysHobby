package com.cms.infrastructure.stock;

import com.cms.domain.stock.Stock;
import com.cms.exception.stock.StockNotFoundException;
import com.cms.domain.stock.infrastructure.StockRepository;
import com.cms.infrastructure.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    @Override
    public List<Stock> findAllByProductNumberIn(List<String> productNumbers) {
        return null;
    }

    @Override
    public Optional<Stock> findByStockId(Long stockId) {
        return stockJpaRepository.findByStockId(stockId)
                .map(StockEntity::toDomain);
    }

    @Override
    public Stock save(Stock stock) {
        return null;
    }

    @Override
    public Integer decreaseStock(Long stockId, Long quantity) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
