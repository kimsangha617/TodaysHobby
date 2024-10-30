package com.cms.service;

import com.cms.domain.Brand;
import com.cms.domain.Category;
import com.cms.repository.BrandRepository;
import com.cms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public List<Category> getCategoryInfo() {
        List<Category> rootCategories = categoryRepository.findByParentCategoryIsNull();

        return rootCategories.stream()
                .map(this::getChildCategories)
                .toList();
    }

    @Transactional(readOnly = true)
    public Category getChildCategories(Category category) {

        // 카테고리를 가져와서 하위 차일드 노드들을 가져온다

        List<Category> categoryList = category.getChildCategories().stream()
                .map(this::getChildCategories)
                .toList();


        return Category.builder()
                .id(category.getId())
                .koreanName(category.getKoreanName())
                .englishName(category.getEnglishName())
                .childCategories(new ArrayList<>(categoryList))
                .build();
    }

    @Transactional
    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }


}
