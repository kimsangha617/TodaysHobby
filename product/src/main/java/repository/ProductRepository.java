package repository;

import domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
        PagingAndSortingRepository<Product,Long>, RevisionRepository<Product, Long, Integer> {

    Optional<Product> findByKoreanName(String productName);
}
