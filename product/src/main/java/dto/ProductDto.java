package dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {

    private String name;
    private String description;
    private List<ProductItemDto.Request> productItemList;

  }

  public static class Response {

  }

}
