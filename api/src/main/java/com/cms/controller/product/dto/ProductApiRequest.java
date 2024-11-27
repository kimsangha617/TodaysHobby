package com.cms.controller.product.dto;


import com.cms.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductApiRequest {

    private Long sellerId;
    private Long categoryId;
    private Long brandId;
    private String koreanName;
    private String englishName;
    private String description;
    private String thumbnailImagePath;
    private List<ProductItemApiRequest> productItemList;

    public boolean isValid() {
//      return StringUitils.isNotBlank(name) && StringUitils.isNotBlank(description);
      return false;
    }

    public static Product toEntity(ProductApiRequest request) {
      return Product.builder()
          .sellerId(request.sellerId)
//          .category(request.categoryId)
//          .brandId(request.brandId)
          .koreanName(request.koreanName)
          .englishName(request.englishName)
          .description(request.description)
          .thumbnailImagePath(request.thumbnailImagePath)
          .build();
    }



}
