package co.istad.suiii.fswd.sbapp.dto;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        BigDecimal price,
        Integer qty,
        String description,
        Integer categoryId


) {
}
