package vn.edu.vtiacademy.demolesson7.repository.postgres;

import jakarta.persistence.criteria.JoinType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Department;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DepartmentSpecification {
    public static Specification<Department> withFilter(DepartmentFilter filter) {
        return Specification.where(withName(filter.name()))
                .and(withDescription(filter.description()))
                .and(withMinId(filter.minId()))
                .and(withMaxId(filter.maxId()))
                .and(withStreetStartWith(filter.streetStartWith()));
    }

    private static Specification<Department> withStreetStartWith(String streetStartWith) {
        if (streetStartWith == null) {
            return null;
        }

        return (root, query, builder) -> {
            var addresses = root.join("addresses", JoinType.INNER);
            return builder.like(addresses.get("street"), streetStartWith + "%");
        };
    }

    private static Specification<Department> withMaxId(Long maxId) {
        if (maxId == null) {
            return null;
        }

        return (root, query, builder) -> builder.lessThan(root.get("id"), maxId);
    }

    private static Specification<Department> withMinId(Long minId) {
        if (minId == null) {
            return null;
        }

        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("id"), minId);
    }

    private static Specification<Department> withDescription(String description) {
        if (description == null) {
            return null;
        }
        return (root, query, builder) -> builder.like(root.get("description"), description + "%");
    }

    private static Specification<Department> withName(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, builder) -> builder.equal(root.get("name"), name);
    }
}
