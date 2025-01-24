package com.cms.infrastructure.product;

import com.cms.domain.product.Product;
import com.cms.infrastructure.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ProductJpaRepository extends
        JpaRepository<ProductEntity, Long>,
        QuerydslPredicateExecutor<ProductEntity>
{
    Optional<ProductEntity> findByKoreanName(String productName);

    @EntityGraph(attributePaths = "productItemList")
    Optional<ProductEntity> findWithProductItemsById(Long id);

    Page<ProductEntity> findByKoreanNameContaining(String koreanName, Pageable pageable);

    Page<ProductEntity> findByBrand_KoreanNameContaining(String brandKoreanName, Pageable pageable);

    Page<ProductEntity> findByCategory_KoreanNameContaining(String categoryKoreanName, Pageable pageable);

    Page<ProductEntity> findBySellerId(Long sellerId, Pageable pageable);


}
