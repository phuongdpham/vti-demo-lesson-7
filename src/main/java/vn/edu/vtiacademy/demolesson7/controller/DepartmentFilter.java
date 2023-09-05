package vn.edu.vtiacademy.demolesson7.controller;

import java.time.LocalDate;

public record DepartmentFilter(
        String name,
        String description,
        Long minId,
        Long maxId,
        String streetStartWith,
        LocalDate fromDate,
        LocalDate toDate
) {
}
