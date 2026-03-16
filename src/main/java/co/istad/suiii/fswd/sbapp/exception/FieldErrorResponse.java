package co.istad.suiii.fswd.sbapp.exception;

public record FieldErrorResponse(
        String filed,
        String reason
) {
}
