package com.cms.domain.product.infrastructure;

import com.cms.domain.product.Product;
import com.cms.infrastructure.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByProductId(Long productId);

    Product save(Product product);

    void deleteAll();

    Optional<Product> findByKoreanName(String productName);

    @EntityGraph(attributePaths = "productItemList")
    Optional<Product> findWithProductItemsById(Long id);

    Page<Product> findByKoreanNameContaining(String koreanName, Pageable pageable);

    Page<Product> findByBrand_KoreanNameContaining(String brandKoreanName, Pageable pageable);

    Page<Product> findByCategory_KoreanNameContaining(String categoryKoreanName, Pageable pageable);

    Page<Product> findBySellerId(Long sellerId, Pageable pageable);
}
