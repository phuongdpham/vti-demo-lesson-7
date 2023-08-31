package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.constraints.NotBlank;

public record AddressReq(
        @NotBlank
        String street,
        @NotBlank
        String city
) {
}
