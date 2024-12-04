package com.cms.infrastructure.sku;

import com.cms.infrastructure.sku.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkuJpaRepository extends JpaRepository<SkuEntity, Long> {

    Optional<SkuEntity> findBySkuId(String skuId);

//    @Lock(value = LockModeType.OPTIMISTIC)
//    @Query("select s from Stock s where s.id = :id")
//    Optional<Stock> findByIdForUpdate(@Param("id") Long id);

//    @Lock(LockModeType.OPTIMISTIC)
//    @Query("SELECT s FROM Stock s WHERE s.sku.skuName = :skuCode")
//    Optional<Stock> findBySkuCodeWithOptimisticLock(@Param("skuCode") String skuCode);

    void deleteAll();

}
