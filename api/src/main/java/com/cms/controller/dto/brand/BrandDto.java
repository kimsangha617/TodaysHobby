package com.cms.controller.dto.brand;

import com.cms.domain.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class BrandDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class BrandSaveRequest {

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

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class BrandInfoResponse {

        private Long brandId;
        private String koreanName;
        private String englishName;
        private String thumbnailImagePath;

//        public Brand toEntity() {
//            return Brand.builder()
//                    .id(this.brandId)
//                    .koreanName(this.koreanName)
//                    .englishName(this.englishName)
//                    .thumbnailImagePath(this.thumbnailImagePath)
//                    .build();
//        }

        public static BrandInfoResponse of(Brand brand) {
            return BrandInfoResponse.builder()
                    .brandId(brand.getId())
                    .koreanName(brand.getKoreanName())
                    .englishName(brand.getEnglishName())
                    .thumbnailImagePath(brand.getThumbnailImagePath())
                    .build();
        }
    }


}
