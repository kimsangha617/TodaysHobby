package domain;

import dto.BrandDto.BrandInfoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "brand")
    private Set<Product> productList = new HashSet<>();

//    @OneToMany
//    private List<Product> productList = new ArrayList<>();

    public BrandInfoResponse toBrandInfoResponse() {
        return BrandInfoResponse.builder()
                .id(this.getId())
                .koreanName(this.getKoreanName())
                .englishName(this.getEnglishName())
                .thumbnailImagePath(this.getThumbnailImagePath())
                .build();
    }


}
