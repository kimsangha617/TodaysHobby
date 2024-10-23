package com.cms.controller.product;

import domain.ProductItem;
import dto.ProductItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProductItemService;
import service.ProductService;

@Slf4j
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductItemService productItemService;
    private final ModelMapper modelMapper;


    @GetMapping("/heartbeat")
    public String hearbeat() {
        log.info("heartbeat......");
        return "111";
    }

    @PostMapping("/")
    public String createProductItem(@Valid ProductItemDto.Request requestDto, Errors errors) {
        ProductItem newProductItem = productItemService.saveProductItem(modelMapper.map(requestDto, ProductItemDto.class));
    }

}
