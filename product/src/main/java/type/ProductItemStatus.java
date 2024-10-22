package type;

import lombok.Getter;

@Getter
public enum ProductItemStatus {
    ON_SALE,
    SOLD_OUT,
    STOP_SELLING,
    DELETED,
}
