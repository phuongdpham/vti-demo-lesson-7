package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressReq(
        @NotBlank
        @Size(max = 512)
        String street,
        @NotBlank
        @Size(max = 512)
        String city
) {
}
