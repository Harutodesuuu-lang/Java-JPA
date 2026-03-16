package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.DataResponse;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import co.istad.suiii.fswd.sbapp.dto.UpdateProductRequest;

public interface ProductService {
    //create a new product
    //Return type
    //parameter
    ProductResponse createProduct(CreateProductRequest createProductRequest);

    ProductResponse getProductByCode(String code);
//    DataResponse getAllProduct(CreateProductRequest createProductRequest);
}
