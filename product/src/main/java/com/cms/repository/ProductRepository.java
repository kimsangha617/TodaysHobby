package com.cms.repository;

import com.cms.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ProductRepository extends
        JpaRepository<Product, Long>,
//        QuerydslBinderCustomizer<QProduct>,
        QuerydslPredicateExecutor<Product>
{


    Optional<Product> findByKoreanName(String productName);

    @EntityGraph(attributePaths = "productItemList")
    Optional<Product> findWithProductItemsById(Long id);

    Page<Product> findByKoreanNameContaining(String koreanName, Pageable pageable);

    Page<Product> findByBrand_KoreanNameContaining(String brandKoreanName, Pageable pageable);

    Page<Product> findByCategory_KoreanNameContaining(String categoryKoreanName, Pageable pageable);

    Page<Product> findBySellerId(Long sellerId, Pageable pageable);
}
