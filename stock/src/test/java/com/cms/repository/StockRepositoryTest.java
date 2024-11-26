package com.cms.repository;

import com.cms.domain.*;
import com.cms.exception.stock.StockNotEnoughException;
import com.cms.type.ProductColor;
import com.cms.type.ProductItemStatus;
import com.cms.type.ProductSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = StockRepository.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.flyway.enabled=false")
@EnableJpaRepositories(basePackages = "com.cms.repository")
@EntityScan(basePackages = "com.cms.domain")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class StockRepositoryTest {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductItemRepository productItemRepository;

    @Autowired
    SkuRepository skuRepository;

    @DisplayName("skuId로 재고를 조회한다")
    @Test
    void findStockBySkuId() {
        //given
        Product product1 = createProduct(1L, "koreanName", "englishName", "description",
                new Category(1L, "카테고리", "category", null, null, 1)
                , new Brand(1L, "브랜드", "brand", "imagePath"), "imagePath");

        ProductItem productItem1 = createProductItem(BigDecimal.valueOf(1000), "S", "RED", "ON_SALE", product1);

        productRepository.save(product1);
        productItemRepository.save(productItem1);

        Sku sku = Sku.createSku(1L, product1.getBrand().getEnglishName(), product1.getEnglishName(), productItem1.getColor(), productItem1.getSize());
        skuRepository.save(sku);

        Stock stock1 = Stock.createStock(1L, 10);
        Stock savedStock = stockRepository.save(stock1);

        //when
        Stock foundStock = stockRepository.findById(1L)
                .orElseThrow( () -> new StockNotEnoughException("재고를 찾을 수 없습니다."));
        //then

        assertThat(foundStock.getQuantity()).isEqualTo(10);
        assertThat(foundStock.getQuantity()).isEqualTo(10);
    }

    @DisplayName("상품번호 리스트로 재고를 조회한다")
    @Test
    void findAllByProductNumberIn() {

        //given
//        Stock stock1 = Stock.createStock(1L, 10);

        //when

        //then

    }



    public ProductItem createProductItem(BigDecimal price, String size, String color, String status, Product product) {
        return ProductItem.builder()
                .price(price)
                .size(ProductSize.valueOf(size))
                .color(ProductColor.valueOf(color))
                .status(ProductItemStatus.valueOf(status))
                .product(product)
                .build();
    }

    public Product createProduct(Long sellerId, String koreanName,
                                 String englishName, String description,
                                 Category category, Brand brand, String imagePath) {
        return Product.builder()
                .sellerId(sellerId)
                .koreanName(koreanName)
                .englishName(englishName)
                .description(description)
                .category(category)
                .brand(brand)
                .thumbnailImagePath(imagePath)
                .build();
    }


}
