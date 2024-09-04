package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private ProductService productService;

    @Transactional
    public void createCategory() {
        productService.createProduct();

        // category save 이건 Product하고는 다른 트랜잭션

    }
}
