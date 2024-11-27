package com.cms.controller.product.dto;

import com.cms.domain.Category;
import com.cms.domain.ProductItem;
import com.cms.type.ProductColor;
import com.cms.type.ProductItemStatus;
import com.cms.type.ProductSize;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductItemApiResponse {
    private Long productId;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private Category category;
    private ProductItemStatus productItemStatus;

    public static ProductItemApiResponse of(ProductItem savedItem) {
        return ProductItemApiResponse.builder()
                .productId(savedItem.getId())
                .price(savedItem.getPrice())
                .productSize(savedItem.getSize())
                .productColor(savedItem.getColor())
                .productItemStatus(savedItem.getStatus())
                .build();
    }
}