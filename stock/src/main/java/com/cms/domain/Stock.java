package com.cms.domain;


import com.cms.exception.stock.StockNotEnoughException;
import com.cms.type.StockStatus;
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
public class Stock extends BaseEntity {

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

    public static Stock createStock(Long skuId, int quantity) {
        return Stock.builder()
                .skuId(skuId)
                .quantity(quantity)
                .stockStatus(StockStatus.IN_STOCK)
                .build();
    }

    public void decreaseStockQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new StockNotEnoughException("재고가 부족합니다.");
        }
        this.quantity -= quantity;
    }

    private void validateQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new StockNotEnoughException("재고가 부족합니다.");
        }
    }

    public void increaseStockQuantity(int quantity) {
        this.quantity += quantity;
    }
}
