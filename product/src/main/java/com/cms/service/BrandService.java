package com.cms.service;

import com.cms.domain.Brand;
import com.cms.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepository;


    @Transactional
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Transactional
    public Brand getBrandInfo(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 브랜드입니다."));
    }

}
