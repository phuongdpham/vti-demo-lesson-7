package vn.edu.vtiacademy.demolesson7.model;

public record AccountFilter(
        String username,
        String nameContains,
        Boolean active
) {
}
