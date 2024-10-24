package domain;

import dto.ProductItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import type.ProductColor;
import type.ProductItemStatus;
import type.ProductSize;

@Getter
@NoArgsConstructor
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
  private Integer price;

  private Integer stockQuantity;

  @Enumerated(EnumType.STRING)
  private ProductSize productSize;

  @Enumerated(EnumType.STRING)
  private ProductColor productColor;

  @Enumerated(EnumType.STRING)
  private ProductItemStatus productItemStatus;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @OneToOne
  @JoinColumn(name = "sku_id")
  private StockKeepingUnit sku;

  public static ProductItem of(Long sellerId, ProductItemDto.SaveRequest productRequestDto) {
    return ProductItem.builder()
        .sellerId(sellerId)
        .name(productRequestDto.getName())
        .price(productRequestDto.getPrice())
        .stockQuantity(productRequestDto.getStockQuantity())
        .build();
  }

}
