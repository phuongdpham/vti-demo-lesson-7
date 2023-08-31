package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.constraints.NotBlank;

import java.util.function.Function;

public record DepartmentReq(
        @NotBlank
        String name,
        String description
) {

    public <R> R transform(Function<? super DepartmentReq, ? extends R> func) {
        return func.apply(this);
    }
}
