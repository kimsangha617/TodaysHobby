package com.cms.infrastructure.product.entity;

import com.cms.domain.BaseEntity;
import com.cms.domain.brand.Brand;
import com.cms.domain.category.Category;
import com.cms.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class ProductEntity extends BaseEntity {
    @Id
    @Column(name = "product_id")
    @Comment("상품 id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "korean_name_changed")
    private String koreanName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "english_name_changed")
    private String englishName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "description_changed")
    private String description;
//    @Builder.Default
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProductItem> productItemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String thumbnailImagePath;



    public Product toDomain() {
        return Product.builder()
                .id(id)
                .sellerId(sellerId)
                .koreanName(koreanName)
                .englishName(englishName)
                .description(description)
                .category(category)
                .brand(brand)
                .thumbnailImagePath(thumbnailImagePath)
                .build();
    }

    public static ProductEntity from(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .koreanName(product.getKoreanName())
                .englishName(product.getEnglishName())
                .description(product.getDescription())
                .category(product.getCategory())
                .brand(product.getBrand())
                .thumbnailImagePath(product.getThumbnailImagePath())
                .build();
    }
}
