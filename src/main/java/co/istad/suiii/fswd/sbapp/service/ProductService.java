package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

public interface ProductService {
    void deleteProduct(String code);

    ProductResponse updateIsAvailable(String code, UpdateIsAvailableRequest updateIsAvailableRequest);



    Page<ProductResponse> getAllProducts(int pageNumber, int pageSize);


    Page<ProductResponse> searchProducts(
            String name,
            String price,
            String qty,
            int pageNumber,
            int pageSize
    );

    ProductResponse updateProductPartiallyByCode(String code, UpdateProductRequest updateProductRequest);
    //create a new product
    //Return type
    //parameter
    ProductResponse createProduct(CreateProductRequest createProductRequest);

    ProductResponse getProductByCode(String code);
//    DataResponse getAllProduct(CreateProductRequest createProductRequest);
}
