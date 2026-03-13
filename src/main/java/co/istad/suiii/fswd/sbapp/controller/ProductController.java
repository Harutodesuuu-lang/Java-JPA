package co.istad.suiii.fswd.sbapp.controller;

import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import co.istad.suiii.fswd.sbapp.dto.UpdateProductRequest;
import co.istad.suiii.fswd.sbapp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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
    public List<ProductResponse> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        log.info("pageNumber: {}, pageSize: {}", pageNumber, pageSize);
        return List.of();
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        log.info("createProductRequest: {}", createProductRequest);
        productService.createProduct(createProductRequest);
        return null;
    }

    @PutMapping("/{code}")
    public ProductResponse updateProductByCode(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProductRequest) {
        log.info("updateProductByCode: {}", updateProductRequest);

        return null;
    }

    @PatchMapping("/{code}")
    public ProductResponse updateProductPartiallyByCode(
            @PathVariable String code,
            @RequestBody UpdateProductRequest updateProductRequest) {
        log.info("updateProductPartiallyByCode: {}", updateProductRequest);

        return null;
    }

    @DeleteMapping("/{code}")
    public ProductResponse deleteProduct(@PathVariable String code) {
        log.info("deleteProduct: {}", code);
        return null;
    }


}
