package service;

import domain.Product;
import domain.ProductItem;
import org.springframework.stereotype.Service;

import dto.ProductItemDto;
import lombok.RequiredArgsConstructor;
import repository.ProductItemRepository;

@RequiredArgsConstructor
@Service
public class ProductItemService {
    
    private final ProductItemRepository productItemRepository;

    public ProductItem saveProductItem(ProductItemDto.Request requestDto) {
        ProductItem newProductItem = productItemRepository.save(requestDto.toEntity());

        return newProductItem;
    }

    
}
