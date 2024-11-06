package com.cms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.util.Map;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class Sku extends BaseEntity {

    @Id
    @Column(name = "sku_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id")
    private ProductItem productItem;

    @OneToOne(mappedBy = "sku")
    private Stock stock;

    @Builder
    private Sku(ProductItem item) {
        this.productItem = item;
        this.skuCode = generateSkuCode(item);

    }

    public static Sku createSku(ProductItem productItem) {
        return Sku.builder()
                .productItem(productItem)
                .skuCode(generateSkuCode(productItem))
                .build();
    }

    private static String generateSkuCode(ProductItem item) {
        return String.format("%s-%s-%s",
                item.getProduct().getBrand(),
                item.getProduct().getEnglishName(),
                item.getColor().name(),
                item.getSize().name()
                );
    }

}
