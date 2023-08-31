package vn.edu.vtiacademy.demolesson7.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Address;
import vn.edu.vtiacademy.demolesson7.model.Department;

public interface DepartmentService {
    Page<Department> findAll(DepartmentFilter filter, Pageable pageable);

    Department createDepartment(Department department);

    Department findById(Long id);

    Department updateDepartment(Long id, Department model);

    void deleteDepartment(Long id);

    Department addAddress(Long id, Address address);
}
