package vn.edu.vtiacademy.demolesson7.controller;

import vn.edu.vtiacademy.demolesson7.model.Role;

import java.time.OffsetDateTime;

public record AccountResp(
        Long id,
        String username,
        String name,
        Role role,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        boolean enable) {
}
