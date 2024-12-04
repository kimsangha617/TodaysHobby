package com.cms.domain.stock;

import com.cms.domain.stock.type.StockStatus;
import com.cms.exception.stock.NegativeQuantityException;
import com.cms.exception.stock.StockNotEnoughException;
import com.cms.infrastructure.stock.entity.StockEntity;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Stock {
    private Long stockId;
    private Integer quantity;
    private StockStatus stockStatus;
    private Long skuId;
    private Long version;

    public static StockEntity createStock(Long skuId, int quantity) {
        return StockEntity.builder()
                .skuId(skuId)
                .quantity(quantity)
                .stockStatus(StockStatus.IN_STOCK)
                .build();
    }

    public void decreaseStockQuantity(int quantity) {
        if (quantity < 0) {
            throw new NegativeQuantityException("재고 차감 quantity는 음수가 될 수 없습니다");
        }

        validateQuantity(quantity);

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
