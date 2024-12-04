package com.cms.infrastructure.sku;

import com.cms.domain.sku.infrastructure.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SkuRepositoryImpl implements SkuRepository {

    private final SkuJpaRepository skuJpaRepository;

}
