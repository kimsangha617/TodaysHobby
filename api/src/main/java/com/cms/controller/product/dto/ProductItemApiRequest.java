package com.cms.controller.product.dto;

import java.math.BigDecimal;
import com.cms.domain.category.Category;
import com.cms.domain.product.type.ProductColor;
import com.cms.domain.productItem.type.ProductItemStatus;
import com.cms.domain.product.type.ProductSize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductItemApiRequest {
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private ProductSize productSize;
    private ProductColor productColor;
    private Category category;
    private ProductItemStatus productItemStatus;
}