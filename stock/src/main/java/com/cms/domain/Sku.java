package com.cms.domain;

import com.cms.type.ProductColor;
import com.cms.type.ProductSize;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_item_id")
//    private ProductItem productItem;
    private Long productId;

    @OneToOne(mappedBy = "sku")
    private Stock stock;

//    @Builder
//    private Sku(ProductItem item) {
//        this.productItem = item;
//        this.skuCode = generateSkuCode(item);
//    }

    public static Sku createSku(Long productId, String brandEngName, String productEngName, ProductColor color, ProductSize size ) {
        return Sku.builder()
                .productId(productId)
                .skuCode(generateSkuCode(brandEngName, productEngName, color, size))
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
