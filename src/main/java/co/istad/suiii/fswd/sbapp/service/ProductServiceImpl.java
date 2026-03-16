package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.Util.DataGenerateUtil;
import co.istad.suiii.fswd.sbapp.domain.Category;
import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.DataResponse;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import co.istad.suiii.fswd.sbapp.mapper.ProductMapper;
import co.istad.suiii.fswd.sbapp.repository.CategoryRepository;
import co.istad.suiii.fswd.sbapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        //To do
        //TODO: Validation data
        Category existingCategory = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));
        log.info("Existing Category : {}", existingCategory.getName());

        // 2. Transfer dto to entity
        Product product = productMapper
                .CreateProductRequestToProduct(createProductRequest);

        product.setCategory(existingCategory);
        product.setCode(DataGenerateUtil.generateProductCode());
        product.setIsAvailable(true);

        //3. Save entity into database using repostitory
       product = productRepository.save(product);

        //4/ Transfer entity to dto
        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse getProductByCode(String code) {
        Product product = productRepository.findByCode(code);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return productMapper.productToProductResponse(product);


    }

//    @Override
//    public DataResponse getAllProduct(CreateProductRequest createProductRequest) {
//        return productRepository.findAll()
//                .stream()
//                .map(this.mapToResponse)
//                .toList();
//    }


}
