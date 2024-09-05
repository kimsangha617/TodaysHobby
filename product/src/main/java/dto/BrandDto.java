package dto;

import domain.Brand;
import lombok.*;

public class BrandDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class BrandInfoResponse {
        private Long id;
        private String koreanName;
        private String englishName;
        private String thumbnailImagePath;

        public Brand toEntity() {
            return Brand.builder()
                    .id(this.getId())
                    .koreanName(this.getKoreanName())
                    .englishName(this.getEnglishName())
                    .thumbnailImagePath(this.getThumbnailImagePath())
                    .build();
        }


    }

    
}
