package com.cms.interfaces.dtos;

import com.cms.common.type.SearchType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductSearchCondition {
    private SearchType searchType;
    private String searchValue;

}
