package com.cms.infrastructure.product;

import com.cms.domain.product.Product;
import com.cms.domain.product.infrastructure.ProductRepository;
import com.cms.infrastructure.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findByProductId(Long productId) {
        return productJpaRepository.findById(productId)
                .map(ProductEntity::toDomain);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
