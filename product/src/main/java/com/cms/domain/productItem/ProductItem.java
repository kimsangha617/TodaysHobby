package com.cms.domain.productItem;

import com.cms.domain.product.type.ProductSize;
import com.cms.domain.productItem.type.ProductItemStatus;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class ProductItem {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private ProductSize size;
    private ProductSize color;
    private ProductItemStatus status;
}
