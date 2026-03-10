package co.istad.suiii.fswd.sbapp.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String name,
        BigDecimal price


) {
}
