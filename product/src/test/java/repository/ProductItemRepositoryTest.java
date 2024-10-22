package repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import domain.Product;
import domain.ProductCategory;
import domain.ProductItem;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

@DataJpaTest
public class ProductItemRepositoryTest {

    @Autowired
    ProductItemRepository productItemRepository;

    @Test
    @DisplayName("판매중인 판매상태를 가진 상품들을 조회한다.")
    void findAllByOnSaleStatus() {
        //given
        ProductItem productItem = createProductItem(1L, ProductItemStatus.ON_SALE, 1000, ProductColor.RED, null, 10, "구두");
        productItem

                //when

        //then

    }

    private Product createProduct(String productNumber,
        ProductItemStatus productItemStatus, int price, ProductColor productColor,
        ProductSize productSize, int stockQuantity,
        String name) {

       return null;
    }

    private ProductItem createProductItem(Long sellerId,
        ProductItemStatus productItemStatus, int price, ProductColor productColor,
        ProductSize productSize, int stockQuantity,
        String name) {

        return ProductItem.builder()
            .sellerId(sellerId)
            .productItemStatus(productItemStatus)
            .name(name)
            .price(price)
            .productColor(productColor)
            .productSize(productSize)
            .stockQuantity(stockQuantity)
        .build();
    }


}
