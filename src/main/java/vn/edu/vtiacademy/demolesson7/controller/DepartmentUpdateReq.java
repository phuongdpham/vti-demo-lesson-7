package vn.edu.vtiacademy.demolesson7.controller;

import vn.edu.vtiacademy.demolesson7.model.Metadata;

import java.util.function.Function;

public record DepartmentUpdateReq(
        String name,
        String description,
        String email,
        Metadata metadata
) {

    public <R> R transform(Function<? super DepartmentUpdateReq, ? extends R> func) {
        return func.apply(this);
    }
}
