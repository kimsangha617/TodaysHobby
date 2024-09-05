package service;

import domain.Product;
import dto.ProductDto.*;
import exception.product.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
  private final ProductRepository productRepository;


  @Transactional
  public Product createProduct(SaveRequest requestDto, Long sellerId) {
    if (productRepository.getReferenceById(requestDto.get))

    return productRepository.save(Product.of(sellerId, requestDto));
  }

  @Transactional
  public ProductInfoResponse findProductInfo(Long productId) {
    return productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId + "에 해당하는 상품이 존재하지 않습니다."))
            .toProductResponseInfo();
  }

  //TODO 카테고리별 상품 조회
  //TODO 검색
  //TODO 상품 이력 관리




}
