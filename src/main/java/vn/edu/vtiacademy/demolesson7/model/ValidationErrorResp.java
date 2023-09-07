package vn.edu.vtiacademy.demolesson7.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public record ValidationErrorResp(
        int status,
        List<Violation> violations
) {
    @Data
    @Builder
    public static class Violation {
        String fieldName;
        String message;
    }
}
