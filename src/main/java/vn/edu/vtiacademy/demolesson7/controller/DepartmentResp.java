package vn.edu.vtiacademy.demolesson7.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import vn.edu.vtiacademy.demolesson7.model.Metadata;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

public record DepartmentResp(
        Long id,
        String name,
        String description,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        List<AddressResp> addresses,
        Set<EmployeeResp> employees,
        Metadata metadata
) {
}
