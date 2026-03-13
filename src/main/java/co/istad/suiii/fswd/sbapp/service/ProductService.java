package co.istad.suiii.fswd.sbapp.service;

import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;

public interface ProductService {
    //create a new product
    //Return type
    //parameter
    ProductResponse createProduct(CreateProductRequest createProductRequest);
}
