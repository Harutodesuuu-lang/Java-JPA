package co.istad.suiii.fswd.sbapp.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateIsAvailableRequest(
        @NotNull(message = "Status isAvailable is required")
        Boolean isAvailable
) {
}
