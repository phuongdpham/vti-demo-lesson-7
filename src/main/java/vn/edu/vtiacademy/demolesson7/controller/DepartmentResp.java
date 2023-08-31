package vn.edu.vtiacademy.demolesson7.controller;

import java.time.OffsetDateTime;

public record DepartmentResp(
        Long id,
        String name,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
