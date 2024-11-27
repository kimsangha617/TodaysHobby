package com.cms.dtos;

import com.cms.domain.Product;
import lombok.*;

import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Long id;
    private Long sellerId;
    private Long categoryId;
    private Long brandId;
    private String koreanName;
    private String englishName;
    private String description;
    private String thumbnailImagePath;
//    private List<ProductItemRequest> productItemList;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getSellerId(),
                product.getCategory().getId(),
                product.getBrand().getId(),
                product.getKoreanName(),
                product.getEnglishName(),
                product.getDescription(),
                product.getThumbnailImagePath()
        );
    }
}
