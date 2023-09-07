package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import vn.edu.vtiacademy.demolesson7.controller.validation.NotExistNameInDB;
import vn.edu.vtiacademy.demolesson7.model.Metadata;

import java.util.List;
import java.util.function.Function;

public record DepartmentReq(
        @NotBlank
        @NotExistNameInDB
        @Size(min = 3, max = 255)
        String name,
        String description,
        @NotBlank
        @Email
        @Size(min = 3, max = 255)
        String email,
        Metadata metadata,
        @NotEmpty
        List<@Valid AddressReq> addresses
) {

    public <R> R transform(Function<? super DepartmentReq, ? extends R> func) {
        return func.apply(this);
    }
}
