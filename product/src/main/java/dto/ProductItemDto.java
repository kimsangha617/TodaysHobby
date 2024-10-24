package dto;

import domain.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

public class ProductItemDto {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class SaveRequest {
    private Long productId;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private ProductCategory productCategory;
    private ProductItemStatus productItemStatus;

  }

}
