package service;

import domain.Product;
import dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
  private final ProductRepository productRepository;


  @Transactional
  public Product addProduct(ProductDto.Request requestDto, Long sellerId) {
    return productRepository.save(Product.of(sellerId, requestDto));
  }

}
