package domain;

import dto.BrandDto.BrandInfo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Entity
public class Brand extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String koreanName;

    @Column(unique = true)
    private String englishName;

    @Column(unique = false)
    private String thumbnailImagePath;

//    @OneToMany
//    private List<Product> productList = new ArrayList<>();

    public BrandInfo toBrandInfo() {
        return BrandInfo.builder()
                .id(this.id)
                .koreanName(this.koreanName)
                .englishName(this.englishName)
                .thumbnailImagePath(this.thumbnailImagePath)
                .build();
    }


}
