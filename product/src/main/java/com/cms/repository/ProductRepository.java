package com.cms.repository;

import com.cms.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product,Long>, RevisionRepository<Product, Long, Integer> {

    Optional<Product> findByKoreanName(String productName);

    @EntityGraph(attributePaths = "productItemList")
    Optional<Product> findWithProductItemsById(Long id);
}
