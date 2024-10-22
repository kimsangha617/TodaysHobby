package domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class StockKeepingUnit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String skuCode;

    // 상품 + 옵션 1개의 skuId;
    // 나이키 포스 / 흰색 / 270 / skuId: 1
    // 나이키 포스 / 검정색 / 270 / skuId: 2
    // 나이키 포스 / 빨간색 / 270 / skuId: 3
    // 나이키 포스 / 흰 / 275 / skuId: 4
    // 나이키 포스 / 빨간색 / 275 / skuId: 5

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    
    private Integer stockQuantity;
    
    private Integer price;

    @OneToOne(mappedBy = "sku")
    private ProductItem productItem;
    
}
