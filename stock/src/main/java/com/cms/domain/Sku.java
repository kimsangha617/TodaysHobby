package com.cms.domain;

import com.cms.type.ProductColor;
import com.cms.type.ProductSize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;


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

    private String skuName;

    private Long productItemId;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;


    public static Sku createSku(Long productId, String brandEngName, String productEngName, ProductColor color, ProductSize size ) {
        return Sku.builder()
                .productItemId(productId)
                .skuName(generateSkuCode(brandEngName, productEngName, color, size))
                .build();
    }

    private static String generateSkuCode(String brandEngName, String productEngName, ProductColor color, ProductSize size) {
        return String.format("%s-%s-%s",
                brandEngName.substring(0, 5).toUpperCase(),
                productEngName.substring(0, 5).toUpperCase(),
                color.name(),
                size.name()
                );
    }

}
