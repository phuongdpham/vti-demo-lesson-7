package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.time.DurationMax;
import vn.edu.vtiacademy.demolesson7.controller.validation.NotExistNameInDB;
import vn.edu.vtiacademy.demolesson7.model.Metadata;

import java.util.List;
import java.util.function.Function;

public record DepartmentReq(
        @NotBlank
        @NotExistNameInDB
        String name,
        String description,
        Metadata metadata,
        @NotEmpty
        List<@Valid AddressReq> addresses
) {

    public <R> R transform(Function<? super DepartmentReq, ? extends R> func) {
        return func.apply(this);
    }
}
