package service;

import domain.Product;
import org.springframework.stereotype.Service;

import dto.ProductItemDto;
import lombok.RequiredArgsConstructor;
import repository.ProductItemRepository;

@RequiredArgsConstructor
@Service
public class ProductItemService {
    
    private final ProductItemRepository productItemRepository;

    public Product saveProduct(ProductItemDto.Request requestDto) {

        return null;
    }

    
}
