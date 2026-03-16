package co.istad.suiii.fswd.sbapp.mapper;


import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Source data => Parameter
    // Target data => Return Type
    ProductResponse productToProductResponse(Product product);

    Product CreateProductRequestToProduct(CreateProductRequest createProductRequest);

}
