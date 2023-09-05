package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeReq(
        @NotBlank
        String name,
        @Email
        @NotNull
        String email
) {
}
