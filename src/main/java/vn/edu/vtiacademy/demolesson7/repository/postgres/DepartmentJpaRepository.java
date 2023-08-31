package vn.edu.vtiacademy.demolesson7.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.vtiacademy.demolesson7.model.Department;

import java.util.Optional;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    Optional<Department> findByName(String name);
    Optional<Department> findByDescription(String name);

    boolean existsByName(String name);
}
