package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductItemDto {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {
    private Long productId;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private ProductSize productSize;
  }

}
