package com.cms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class Brand extends BaseEntity{

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String koreanName;

    @Column(unique = true)
    private String englishName;

    @Column(unique = false)
    private String thumbnailImagePath;

//    @Builder.Default
//    @OneToMany(mappedBy = "brand")
//    private Set<Product> productList = new HashSet<>();
}
