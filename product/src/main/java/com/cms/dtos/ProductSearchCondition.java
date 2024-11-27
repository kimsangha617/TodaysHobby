package com.cms.dtos;

import com.cms.type.SearchType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductSearchCondition {
    private SearchType searchType;
    private String searchValue;

}
