package com.cms.service;

import com.cms.domain.brand.Brand;
import com.cms.domain.category.Category;
import com.cms.domain.product.Product;
import com.cms.domain.product.infrastructure.ProductRepository;
import com.cms.domain.product.service.ProductService;
import com.cms.domain.product.type.ProductColor;
import com.cms.domain.product.type.ProductSize;
import com.cms.domain.productItem.ProductItem;
import com.cms.domain.productItem.type.ProductItemStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService sut;
    @Mock
    private ProductRepository productRepository;

    @DisplayName("신규 상품을 등록한다")
    @Test
    void createProduct_thenReturnOk() {
        //given
        Product mockProduct = createProduct();
        given(productRepository.save(any(Product.class)))
                .willReturn(mockProduct)
                ;

        //when


        //then
    }

    private Product createProduct() {
        return Product.builder()
                .id(1L)
                .sellerId(1L)
                .koreanName("테스트상품")
                .englishName("test product")
                .brand(createBrand())
                .category(createCategory())
                .description("테스트입니다")
                .build();
    }

    private Brand createBrand() {
        return Brand.builder()
                .id(1L)
                .koreanName("테스트브랜드")
                .englishName("test brand")
                .thumbnailImagePath("http://localhost:8080/brand/test")
                .build();
    }

    private Category createCategory() {
        return Category.builder()
                .id(1L)
                .koreanName("테스트브랜드")
                .englishName("test brand")
                .parentCategory(null)
                .childCategories(null)
                .depthLevel(1)
                .build();
    }
}