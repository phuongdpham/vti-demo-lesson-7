package vn.edu.vtiacademy.demolesson7.repository.postgres;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Department;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DepartmentSpecification {
    public static Specification<Department> withFilter(DepartmentFilter filter) {
        return Specification.where(withName(filter.name()))
                .and(withDescription(filter.description()));
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
