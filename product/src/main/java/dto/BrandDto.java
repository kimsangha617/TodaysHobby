package dto;

import domain.Brand;
import lombok.*;

public class BrandDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class BrandInfo {
        private Long id;
        private String koreanName;
        private String englishName;
        private String thumbnailImagePath;

        public Brand toEntity() {
            return Brand.builder()
                    .id(this.id)
                    .koreanName(this.koreanName)
                    .englishName(this.englishName)
                    .thumbnailImagePath(this.thumbnailImagePath)
                    .build();
        }


    }
}
