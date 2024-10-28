package dto;

import java.math.BigDecimal;

import domain.ProductCategory;
import domain.ProductItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

public class ProductItemDto {


  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor
  @Builder
  public static class SaveRequest {
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
  public static class SaveResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private ProductCategory productCategory;
    private ProductItemStatus productItemStatus;
  }

public static SaveResponse from(ProductItem savedItem) {
  return SaveResponse.builder()
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
