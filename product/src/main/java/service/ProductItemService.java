package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductItemService {

    private ProductService productService;

    @Transactional
    public void createProductItem() {
        productService.createProduct_new();
        //productItem 만들거야 근데 나는 한트랜잭션으로 묶어서 Item이 실패하면 Product도 롤백할거야.
    }
}
