package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.domain.Category;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import co.istad.suiii.fswd.sbapp.repository.CategoryRepository;
import co.istad.suiii.fswd.sbapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ggProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        //To do
        //ToD
        Category existingCategory = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(()-> new RuntimeException("Category Not Found"));
        log.info("Existing Category : {}", existingCategory.getName());
        return null;
    }
}
