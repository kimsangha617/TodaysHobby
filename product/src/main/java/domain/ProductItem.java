package domain;

import dto.ProductItemDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class ProductItem extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long sellerId;

  @Audited
  private String name;

  @Audited
  private BigDecimal price;

  private Integer stockQuantity;

  @Enumerated(EnumType.STRING)
  private ProductSize size;

  @Enumerated(EnumType.STRING)
  private ProductColor color;

  @Enumerated(EnumType.STRING)
  private ProductItemStatus status;

  @Enumerated(EnumType.STRING)
  private ProductCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @OneToOne
  @JoinColumn(name = "sku_id")
  private StockKeepingUnit sku;

  public static ProductItem of(Product productRef, ProductItemDto.SaveRequest productRequestDto) {
    return ProductItem.builder()
        .product(productRef)
        .color(productRequestDto.getProductColor())
        .size(productRequestDto.getProductSize())
        .name(productRequestDto.getName())
        .category(productRequestDto.getProductCategory())
        .status(ProductItemStatus.ON_SALE)
        .price(productRequestDto.getPrice())
        .stockQuantity(productRequestDto.getStockQuantity())
        .build();
  }

}
