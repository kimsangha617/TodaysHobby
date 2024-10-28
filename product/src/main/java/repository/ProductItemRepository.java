package repository;

import domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    @Query("SELECT pi FROM ProductItem pi JOIN FETCH pi.product WHERE pi.id = :id")
    ProductItem findByIdWithProduct(@Param("id") Long id);

}
