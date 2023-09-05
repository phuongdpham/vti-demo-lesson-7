package vn.edu.vtiacademy.demolesson7.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Department;
import vn.edu.vtiacademy.demolesson7.model.Employee;

import java.util.Optional;

public interface DepartmentRepository {
    Page<Department> findAll(DepartmentFilter filter, Pageable pageable);

    Department save(Department department);

    Optional<Department> findById(Long id);

    Optional<Department> findByName(String name);

    void deleteById(Long id);

    boolean existsByName(String name);
}
