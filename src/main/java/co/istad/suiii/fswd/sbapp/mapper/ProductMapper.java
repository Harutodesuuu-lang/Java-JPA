package co.istad.suiii.fswd.sbapp.mapper;


import co.istad.suiii.fswd.sbapp.domain.Product;
import co.istad.suiii.fswd.sbapp.dto.CreateProductRequest;
import co.istad.suiii.fswd.sbapp.dto.ProductResponse;
//import co.istad.suiii.fswd.sbapp.dto.UpdateIsAvailableRequest;
import co.istad.suiii.fswd.sbapp.dto.UpdateProductRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductRequestToProductPartially(
            UpdateProductRequest updateProductRequest,
            @MappingTarget Product product);
    // Source data => Parameter
    // Target data => Return Type

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateIsAvailable( UpdateIsAvailableRequest updateIsAvailableRequest,
//            @MappingTarget Product product
//    );

    ProductResponse productToProductResponse(Product product);

    Product CreateProductRequestToProduct(CreateProductRequest createProductRequest);

}
