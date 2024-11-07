package com.cms.controller.brand.dto;

import com.cms.domain.Brand;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BrandInfoResponse {
    private Long brandId;
    private String koreanName;
    private String englishName;
    private String thumbnailImagePath;

    public static BrandInfoResponse of(Brand brand) {
        return BrandInfoResponse.builder()
                .brandId(brand.getId())
                .koreanName(brand.getKoreanName())
                .englishName(brand.getEnglishName())
                .thumbnailImagePath(brand.getThumbnailImagePath())
                .build();
    }
}
