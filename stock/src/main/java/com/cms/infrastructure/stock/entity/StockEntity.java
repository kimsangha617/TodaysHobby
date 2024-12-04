package com.cms.infrastructure.stock.entity;


import com.cms.domain.BaseEntity;
import com.cms.domain.stock.Stock;
import com.cms.exception.stock.NegativeQuantityException;
import com.cms.exception.stock.StockNotEnoughException;
import com.cms.domain.stock.type.StockStatus;
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
public class StockEntity extends BaseEntity {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_quantity")
    private Integer quantity;

    @Builder.Default
    @Column(name = "stock_status")
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus = StockStatus.IN_STOCK;

    private Long skuId;

    @Version
    private Long version;

    public Stock toDomain() {
        return Stock.builder()
                .stockId(id)
                .quantity(quantity)
                .stockStatus(stockStatus)
                .skuId(skuId)
                .version(version)
                .build();
    }

    public static StockEntity fromDomain(Stock stock) {
        return new StockEntity(
                stock.getStockId(),
                stock.getQuantity(),
                stock.getStockStatus(),
                stock.getSkuId(),
                stock.getVersion()
        );
    }
}
