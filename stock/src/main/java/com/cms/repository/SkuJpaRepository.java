package com.cms.repository;

import java.util.Optional;

public interface SkuJpaRepository {

    Optional<SkuEntity> findBySkuId(String skuId);

}
