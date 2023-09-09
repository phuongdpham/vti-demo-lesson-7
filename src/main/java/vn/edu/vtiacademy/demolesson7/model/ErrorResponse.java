package vn.edu.vtiacademy.demolesson7.model;

public record ErrorResponse(
        int status,
        String code,
        String message,
        String description
) {
}
