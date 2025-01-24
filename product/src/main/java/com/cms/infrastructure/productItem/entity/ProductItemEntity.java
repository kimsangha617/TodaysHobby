package com.cms.infrastructure.productItem.entity;

import com.cms.domain.BaseEntity;
import com.cms.domain.product.type.ProductColor;
import com.cms.domain.product.type.ProductSize;
import com.cms.domain.productItem.type.ProductItemStatus;
import com.cms.infrastructure.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class ProductItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_item_id")
    private Long id;

    @Audited
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductSize size;

    @Enumerated(EnumType.STRING)
    private ProductColor color;

    @Enumerated(EnumType.STRING)
    private ProductItemStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    //TODO Stock 과의 연관관계 맵핑을 해제 하면서 데이터 처리를 어떻게 할것인지

}
