package repository;

import domain.Product;
import domain.ProductItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = ProductItemRepository.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.flyway.enabled=false")
@DataJpaTest
public class ProductItemRepositoryTest {

  @Autowired
  private ProductItemRepository productItemRepository;

  @Test
  @DisplayName("판매중 상태를 가진 상품들을 조회한다.")
  void findAllByOnSaleStatus() {
    //given
    ProductItem productItem = createProductItem(1L, ProductItemStatus.ON_SALE,
        1000, ProductColor.RED, ProductSize.XL, 10, "구두");
    ProductItem productItem2 = createProductItem(1L, ProductItemStatus.ON_SALE,
        2000, ProductColor.WHITE, ProductSize.L, 10, "구두");

    //when
    List<ProductItem> productItems = productItemRepository.saveAll(
        List.of(productItem, productItem2));

    //then
    assertThat(productItems).hasSize(2);

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
