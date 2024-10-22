package domain;

import dto.ProductDto;
import dto.ProductDto.ProductInfoResponse;
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
    // @Audited(withModifiedFlag = true, modifiedColumnName = "korean_name_changed")
    private String koreanName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "english_name_changed")
    private String englishName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "description_changed")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductItem> productItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @ManyToOne
    private Brand brand;

    private String thumbnailImagePath;


    public static Product of(Long sellerId, ProductDto.SaveRequest productRequestDto) {
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

    public ProductInfoResponse toProductResponseInfo() {
        return ProductInfoResponse.builder()
                .id(this.getId())
                .englishName(this.getEnglishName())
                .koreanName(this.getKoreanName())
                .description(this.getDescription())
                .thumbnailImagePath(this.getThumbnailImagePath())
                .brandInfoResponse(this.getBrand().toBrandInfoResponse())
                .build();
    }
}
