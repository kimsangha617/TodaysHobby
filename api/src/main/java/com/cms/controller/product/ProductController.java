package com.cms.controller.product;

import domain.Product;
import dto.ProductDto;
import dto.ProductItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

@Slf4j
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;


    @GetMapping("/heartbeat")
    public String hearbeat() {
        log.info("heartbeat......");
        return "111";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity createProduct(@Valid ProductDto.SaveRequest requestDto, Long sellerId, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest(errors);
//          return errors.getAllErrors().get(0).getDefaultMessage();
        }
//        Product가 있는지 확인하고
//        존재하면 Exception
//        없으면 만든다
        productService.checkProductExists(requestDto.getKoreanName());
        Product newProduct = productService.saveProduct(modelMapper.map(requestDto, Product.class), sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(newProduct.getId());
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public String createProductItem(@Valid ProductItemDto.SaveRequest requestDto, Errors errors) {
//        ProductItem newProductItem = productService.saveProductItem(modelMapper.map(requestDto, ProductItemDto.class));
        return null;
    }

}
