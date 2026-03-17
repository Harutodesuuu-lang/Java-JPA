package co.istad.suiii.fswd.sbapp.controller;

import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import co.istad.suiii.fswd.sbapp.dto.UpdateProductRequest;
import co.istad.suiii.fswd.sbapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public Page<ProductResponse> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        log.info("pageNumber: {}, pageSize: {}", pageNumber, pageSize);
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(@PathVariable String code) {
        log.info("getProductByCode: {}", code);

        return productService.getProductByCode(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest) {
        log.info("createProductRequest: {}", createProductRequest);

        return productService.createProduct(createProductRequest);
    }

    @PutMapping("/{code}")
    public ProductResponse updateIsAvaliableByCode(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProductRequest) {
        log.info("updateProductByCode: {}", updateProductRequest);

        return null;
    }

    @PatchMapping("/{code}")
    public ProductResponse updateProductPartiallyByCode(
            @PathVariable String code,
            @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        log.info("updateProductPartiallyByCode: {}", updateProductRequest);

        return productService.updateProductPartiallyByCode(code, updateProductRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteProduct(@PathVariable String code) {
        log.info("deleteProduct: {}", code);
        productService.deleteProduct(code);
    }

    @GetMapping("/search")
    public Page<ProductResponse> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String qty,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize
    ) {
        log.info("searchProducts name={}, price={}, qty={}", name, price, qty);

        return productService.searchProducts(name, price, qty, pageNumber, pageSize);
    }

}
