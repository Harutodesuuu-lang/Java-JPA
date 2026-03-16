package co.istad.suiii.fswd.sbapp.dto;

import lombok.Builder;

import java.math.BigDecimal;




@Builder
public record ProductResponse(
        String code,
        String name,
        String qty,
        BigDecimal price,
        Boolean isAvailable,
        String description


) {
}
