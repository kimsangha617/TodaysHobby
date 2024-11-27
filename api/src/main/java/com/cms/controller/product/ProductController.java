package com.cms.controller.product;

import com.cms.controller.product.dto.ProductApiRequest;
import com.cms.controller.product.dto.ProductApiResponse;
import com.cms.type.SearchType;
import com.cms.domain.Product;
import com.cms.dtos.ProductResponseDto;
import com.cms.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
//    private final ModelMapper modelMapper;


    @GetMapping("/heartbeat")
    public String hearbeat() {
        log.info("heartbeat......");
        return "111";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity createProduct(@Valid @RequestBody ProductApiRequest requestDto, Errors errors) {
        log.info("request: {}", requestDto);
        if (errors.hasErrors()) {
            return badRequest(errors);
//          return errors.getAllErrors().get(0).getDefaultMessage();
        }
//        Product가 있는지 확인하고
//        존재하면 Exception
//        없으면 만든다
        // productService.checkProductExists(requestDto.getKoreanName());
//        Product newProduct = productService.saveProduct(modelMapper.map(requestDto, Product.class), sellerId);
        Product newProductInstance = ProductApiRequest.toEntity(requestDto);
        Product newProduct = productService.createProduct(newProductInstance);
        return ResponseEntity.status(HttpStatus.OK).body(newProduct.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<List<ProductApiResponse>> getProductsByCondition(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ProductResponseDto> productResponseDtos = productService.searchProductsByCondition(
                searchType, searchValue, pageable
        );
//        return ResponseEntity.status(HttpStatus.OK).body();
        return null;
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
    }

}
