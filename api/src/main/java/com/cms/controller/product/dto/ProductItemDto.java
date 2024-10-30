package com.cms.controller.product.dto;

import java.math.BigDecimal;

import com.cms.domain.ProductCategory;
import com.cms.domain.ProductItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.cms.type.ProductColor;
import com.cms.type.ProductItemStatus;
import com.cms.type.ProductSize;

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
    private ProductCategory productCategory;
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
    private ProductCategory productCategory;
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
    .productCategory(savedItem.getCategory())
    .productItemStatus(savedItem.getStatus())
  .build();
}


}
