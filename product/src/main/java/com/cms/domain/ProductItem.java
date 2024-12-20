package com.cms.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import com.cms.type.ProductColor;
import com.cms.type.ProductItemStatus;
import com.cms.type.ProductSize;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class ProductItem extends BaseEntity {
  @Id
  @Column(name = "product_item_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  private Long sellerId;

//  @Audited
//  private String name;

  @Audited
  private BigDecimal price;

//  private Integer stockQuantity;

  @Enumerated(EnumType.STRING)
  private ProductSize size;

  @Enumerated(EnumType.STRING)
  private ProductColor color;

  @Enumerated(EnumType.STRING)
  private ProductItemStatus status;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "category_id")
//  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

//  @OneToOne
//  @JoinColumn(name = "sku_id")
//  private Sku sku;

  //TODO Stock 과의 연관관계 맵핑을 해제 하면서 데이터 처리를 어떻게 할것인지

}
