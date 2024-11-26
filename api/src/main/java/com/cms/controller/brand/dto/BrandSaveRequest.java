package com.cms.controller.brand.dto;

import com.cms.domain.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BrandSaveRequest {

    @NotBlank(message = "브랜드 한글명을 입력해주세요.")
    private String koreanName;
    @NotBlank(message = "브랜드 영문명을 입력해주세요.")
    private String englishName;

    private String thumbnailImagePath;

    public static Brand toEntity(BrandSaveRequest request) {
        return Brand.builder()
                .koreanName(request.koreanName)
                .englishName(request.englishName)
                .thumbnailImagePath(request.thumbnailImagePath)
                .build();
    }
}
