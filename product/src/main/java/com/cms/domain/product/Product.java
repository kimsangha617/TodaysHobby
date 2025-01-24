package com.cms.domain.product;

import com.cms.domain.productItem.ProductItem;
import com.cms.domain.brand.Brand;
import com.cms.domain.category.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Product {
    private Long id;
    private Long sellerId;
    private String koreanName;
    private String englishName;
    private String description;
//    private List<ProductItem> productItemList;
    private Category category;
    private Brand brand;
    private String thumbnailImagePath;

//    public void addProductItem(ProductItem productItem) {
//        if (productItem == null) {
//            productItemList = new ArrayList<>();
//        }
//        productItemList.add(productItem);
//    }




}
