package co.istad.suiii.fswd.sbapp.dto;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        BigDecimal price,
        Integer qty,
        String description
) {
}
