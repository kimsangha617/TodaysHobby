package com.cms.controller.product.dto;

import com.cms.domain.Category;
import com.cms.domain.ProductItem;
import com.cms.type.ProductColor;
import com.cms.type.ProductItemStatus;
import com.cms.type.ProductSize;
import lombok.*;

import java.math.BigDecimal;

public class ProductItemDto {


  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor
  @Builder
  public static class ProductItemSaveRequest {
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private Category category;
    private ProductItemStatus productItemStatus;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor
  @Builder
  public static class ProductItemInfoResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private Category category;
    private ProductItemStatus productItemStatus;
  }

public static ProductItemInfoResponse of(ProductItem savedItem) {
  return ProductItemInfoResponse.builder()
    .productId(savedItem.getId())
    .name(savedItem.getName())
    .price(savedItem.getPrice())
    .stockQuantity(savedItem.getStockQuantity())
    .productSize(savedItem.getSize())
    .productColor(savedItem.getColor())
    .category(savedItem.getCategory())
    .productItemStatus(savedItem.getStatus())
  .build();
}


}
