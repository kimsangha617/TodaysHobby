package com.cms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "korean_name_changed")
    private String koreanName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "english_name_changed")
    private String englishName;
    // @Audited(withModifiedFlag = true, modifiedColumnName = "description_changed")
    private String description;

     @Builder.Default
     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
     private List<ProductItem> productItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String thumbnailImagePath;

}
