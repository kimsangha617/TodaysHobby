package domain;

import dto.ProductDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private String koreanName;
    private String englishName;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductItem> productItemList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String originImagePath;
    private String thumbnailImagePath;


    public static Product of(Long sellerId, ProductDto.Request productRequestDto) {
        return Product.builder()
                .sellerId(sellerId)
                .koreanName(productRequestDto.getKoreanName())
                .englishName(productRequestDto.getEnglishName())
                .description(productRequestDto.getDescription())
                .productItemList(productRequestDto.getProductItemList().stream()
                        .map(productItem -> ProductItem.of(sellerId, productItem))
                        .collect(Collectors.toList()))
                .build();
    }

    public Product toProductResponseInfo() {
        return null;
    }
}
