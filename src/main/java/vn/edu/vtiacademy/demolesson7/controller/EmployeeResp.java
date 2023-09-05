package vn.edu.vtiacademy.demolesson7.controller;

import java.time.OffsetDateTime;

public record EmployeeResp(
        Long id,
        String name,
        String email,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
