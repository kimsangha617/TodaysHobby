package com.cms.controller.product.dto;

import com.cms.domain.Product;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductInfoResponse {
      private Long id;
      private Long sellerId;
      private Long categoryId;
      private Long brandId;
      private String koreanName;
      private String englishName;
      private String description;
      //    private BrandDto brandDto; brandDto 의 값이 들어가야함 엔티티값이아니라
//    private BrandInfoResponse brandInfoResponse;
      private String thumbnailImagePath;

       //TODO category, brand 에 대해 어떻게 처리할건지 고민 후 개발
      public static ProductInfoResponse of(Product product) {
        return ProductInfoResponse.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
//                .categoryId(product.getCategoryId())
//                .brandId(product.getBrandId())
                .koreanName(product.getKoreanName())
                .englishName(product.getEnglishName())
                .description(product.getDescription())
                .thumbnailImagePath(product.getThumbnailImagePath())
                .build();
      }
}
