package domain;

import dto.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@Setter
@NoArgsConstructor
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
  private String name;
  private String description;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  private List<ProductItem> productItemList = new ArrayList<>();


  public static Product of(Long sellerId, ProductDto.Request productRequestDto) {
    return Product.builder()
        .sellerId(sellerId)
        .name(productRequestDto.getName())
        .description(productRequestDto.getDescription())
        .productItemList(productRequestDto.getProductItemList().stream()
            .map(productItem -> ProductItem.of(sellerId, productItem))
            .collect(Collectors.toList()))
        .build();
  }
}
