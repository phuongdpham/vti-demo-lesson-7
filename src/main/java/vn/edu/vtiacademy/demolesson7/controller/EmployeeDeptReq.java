package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.constraints.*;

public record EmployeeDeptReq(
        @NotNull
        @Positive
        Long id
) {
}
