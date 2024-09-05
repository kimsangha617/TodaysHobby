package dto;

import java.util.List;

import dto.BrandDto.BrandInfoResponse;
import lombok.*;

public class ProductDto {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class SaveRequest {

    private String koreanName;
    private String englishName;
    private String description;
    private List<ProductItemDto.Request> productItemList;

    boolean isValid() {
      return StringUitils.isNotBlank(name) && StringUitils.isNotBlank(description);
    }
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
//    private BrandDto brandDto; brandDto 의 값이 들어가야함 엔티티값이아니라
    private BrandInfoResponse brandInfoResponse;
    private String thumbnailImagePath;



  }

}
