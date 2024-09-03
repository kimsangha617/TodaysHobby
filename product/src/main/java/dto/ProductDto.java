package dto;

import java.util.List;

import lombok.*;

public class ProductDto {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {

    private String koreanName;
    private String englishName;
    private String description;
    private List<ProductItemDto.Request> productItemList;

  }

  public static class Response {

  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor
  @Builder
  public static class ProductInfoResponse {
    private Long id;
    private String koreanName;
    private String englishName;
    private String description;


  }

}
