package domain;

import dto.ProductItemDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import type.ProductSize;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

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

  private ProductSize productSize;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public static ProductItem of(Long sellerId, ProductItemDto.Request productRequestDto) {
    return ProductItem.builder()
        .sellerId(sellerId)
        .name(productRequestDto.getName())
        .price(productRequestDto.getPrice())
        .stockQuantity(productRequestDto.getStockQuantity())
        .build();
  }

}
