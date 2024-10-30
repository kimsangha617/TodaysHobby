package com.cms.service;

import com.cms.domain.Product;
import com.cms.domain.ProductItem;
import com.cms.exception.product.ProductNotFoundException;
import com.cms.repository.ProductItemRepository;
import com.cms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductItemRepository productItemRepository;



    /**
    // if (productRepository.getReferenceById(requestDto.get()))

    // public 에 대한 방어 처리라고 보면 되겠다
    {
      // Request Validation 처리가 필수
      if (requestDto == null) {
        throw new IllegalArgumentException("requestDto is null");
      }
      if (sellerId == null) {
        throw new IllegalArgumentException("sellerId is null");
      }
        if (productRepository.findByKoreanName(requestDto.getKoreanName()).isPresent() ) {
            throw new IllegalArgumentException(requestDto.getKoreanName() + "이 존재합니다.");
        }
    }

    if (!requestDto.isValid()) {
      throw new IllegalArgumentException("requestDto is not valid");
    }

    // Save validation ( 제약사항 )
    // 중복 이름 허용 불가
    // business 적 제약사항
    // 1. 상품명이 동일하지 않아야 한다.
    // 2. 셀러가 동일 상품을 2개 이상 등록 할 수 없다.
    // 3. usable이 false 이면 신규로 동일한 이름의 상품을 등록할 수 있다? 없다 ?
    //    3-1 기존 상품을 찾아서 동일 이름이 있는 상품이 있으면 -> Exception 발생
    //    3-2 usable이 false 인 경우는 신규로 동일한 이름의 상품을 등록 할 수 없다

    //    메소드 진입하면 parameter 에 대한 validation 이 우선
    //        그 후 비즈니스 로직 적인 validation 진행하는게 클린코드에서 말하는 방법

    //    저장에 필요한 정보 조회 + 저장 로직
    */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Product createProduct(Product product) {

    return productRepository.save(product);
  }

  @Transactional
  public ProductItem addProductItems(Long productId, ProductItem productItem) {
    
    // Product productRef = productRepository.getReferenceById(productId);
                        // .orElseThrow( () -> new ProductNotFoundException("상품을 찾을 수 없습니다."))

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("상품이 존재하지 않습니다. productId: " + productId));
    

    ProductItem savedItem = productItemRepository.save(productItem);

    return productItemRepository.save(savedItem);
    // createProductItems(saveProduct, itemRequests)

  }



//  @Transactional(readOnly = true)
//  public ProductInfoResponse getProductInfoByProductName(String productName) {
//    if (productName == null || productName.isEmpty()) {
//      throw new IllegalArgumentException("productName is null or empty");
//    }
//
//    return productRepository.findByKoreanName(productName)
//            .orElseThrow(() -> new ProductNotFoundException(productName + "이 존재하지 않습니다."))
//            .toProductResponseInfo();
//
//  }

//  @Transactional(readOnly = true)
//  public ProductInfoResponse getProductInfoByProductId(Long productId) {
//    if (productId == null) {
//      throw new IllegalArgumentException("productId is null");
//    }
//    return productRepository.findById(productId)
//            .orElseThrow(() -> new ProductNotFoundException(productId + "에 해당하는 상품이 존재하지 않습니다."))
//            .toProductResponseInfo();
//  }

//  public Page<Product> getProductsPagedAndSorted(int page, int size, String sortDirection) {
//    if (sortDirection == null || sortDirection.isEmpty()) {
//      sortDirection = "DESC";
//    }
//
//    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "price"));
//    return productRepository.findAll(pageable);
//  }

  public List<Product> getAllProducts() {
    return null;
  }


  //TODO 카테고리별 상품 조회
  //TODO 검색
  //TODO 상품 이력 관리




}
