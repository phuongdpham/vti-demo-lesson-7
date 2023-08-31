package vn.edu.vtiacademy.demolesson7.controller;

public record DepartmentFilter(
        String name,
        String description,
        Long minId,
        Long maxId,
        String streetStartWith
) {
}
