package vn.edu.vtiacademy.demolesson7.controller.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vn.edu.vtiacademy.demolesson7.model.Role;

public record AdminAccountReq(
        @NotBlank
        @Size(min = 4, max = 64)
        String username,
        @NotBlank
        @Size(min = 3, max = 256)
        String name,

        @NotNull
        Role role
) {
}
