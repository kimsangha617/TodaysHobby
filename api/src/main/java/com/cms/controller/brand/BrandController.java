package com.cms.controller.brand;

import com.cms.controller.brand.dto.BrandDto;
import com.cms.domain.Brand;
import com.cms.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/{id}")
    public BrandDto.BrandInfoResponse getBrandInfo(@PathVariable Long id) {
        Brand brand = brandService.getBrandInfo(id);
//        brand 단의 내부 로직은 몰라야 하기 때문에 dto 에서 변환
        return BrandDto.BrandInfoResponse.of(brand);
    }

    @PostMapping("")
    public BrandDto.BrandInfoResponse createBrand(@RequestBody BrandDto.BrandSaveRequest requestDto) {
        log.info("request: {}", requestDto);
        Brand newBrandInstance = BrandDto.BrandSaveRequest.toEntity(requestDto);
        Brand brand = brandService.createBrand(newBrandInstance);
        return BrandDto.BrandInfoResponse.of(brand);
    }

}
