package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.Util.DataGenerateUtil;
import co.istad.suiii.fswd.sbapp.Util.ProductSpecification;
import co.istad.suiii.fswd.sbapp.domain.Category;
import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.*;
import co.istad.suiii.fswd.sbapp.mapper.ProductMapper;
import co.istad.suiii.fswd.sbapp.repository.CategoryRepository;
import co.istad.suiii.fswd.sbapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public void deleteProduct(String code) {
        log.info("code: {}", code);
        //1. Validate product code exist or not
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found "));
        productRepository.delete(product);
    }

    @Override
    public ProductResponse updateIsAvailable(String code, UpdateIsAvailableRequest updateIsAvailableRequest) {
       log.info("UpdateIsAvailable: {} and new data {}", code, updateIsAvailableRequest);
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found "));

        product.setIsAvailable(updateIsAvailableRequest.isAvailable());

        return productMapper.productToProductResponse(productRepository.save(product));
    }


    @Override
    public Page<ProductResponse> getAllProducts(int pageNumber, int pageSize) {
        log.info("getAllProducts with page number {} and page size {}", pageNumber, pageSize);
        //1. setup page request
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        //2. Find data from database with page request
        Page<Product> productResponsePage = productRepository.findByIsAvailableTrue(pageable);

        return productResponsePage.map(productMapper::productToProductResponse);
    }

    @Override
    public Page<ProductResponse> searchProducts(String name, String price, String qty, int pageNumber, int pageSize) {
        log.info("Search products with name={}, price={}, qty={}", name, price, qty);

        // 1. Create pageable
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // 2. Build specification (dynamic query)
        Specification<Product> spec = Specification
                .where(ProductSpecification.nameContains(name))
                .and(ProductSpecification.priceFilter(price))
                .and(ProductSpecification.qtyFilter(qty));

        // 3. Query database
        Page<Product> productPage = productRepository.findAll(spec, pageable);

        // 4. Map Entity → DTO
        return productPage.map(productMapper::productToProductResponse);
    }

    @Override
    public ProductResponse updateProductPartiallyByCode(String code, UpdateProductRequest updateProductRequest) {
        log.info("updateProduct with code {}", code);
        //1. Validate product code exist or not
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found "));
        //2. Write partially update logic
        productMapper.updateProductRequestToProductPartially(updateProductRequest, product);


        product = productRepository.save(product);
        return productMapper.productToProductResponse(product);
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
