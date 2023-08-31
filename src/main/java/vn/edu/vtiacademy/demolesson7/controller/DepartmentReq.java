package vn.edu.vtiacademy.demolesson7.controller;

import java.util.function.Function;

public record DepartmentReq(
        String name,
        String description
) {

    public <R> R transform(Function<? super DepartmentReq, ? extends R> func) {
        return func.apply(this);
    }
}
