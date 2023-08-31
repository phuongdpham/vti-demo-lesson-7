package vn.edu.vtiacademy.demolesson7.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Address;
import vn.edu.vtiacademy.demolesson7.model.Department;
import vn.edu.vtiacademy.demolesson7.model.DepartmentExistedException;
import vn.edu.vtiacademy.demolesson7.model.DepartmentNotFoundException;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainDepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;

    @Override
    public Page<Department> findAll(DepartmentFilter filter, Pageable pageable) {
        return departmentRepository.findAll(filter, pageable);
    }

    @Override
    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new DepartmentExistedException(HttpStatus.CONFLICT.value(), "C2", "Department with name=" + department.getName() + " already exists");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND.value(), "C1", "Department not found with given id=" + id));
    }

    @Override
    public Department updateDepartment(Long id, Department model) {
        var existingDepartment = findById(id);

        if (model.getName() != null) {
            existingDepartment.setName(model.getName());
        }

        if (model.getDescription() != null) {
            existingDepartment.setDescription(model.getDescription());
        }

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department addAddress(Long id, Address address) {
        var department = findById(id);

        department.getAddresses().add(address);

        address.setDepartment(department);

        return departmentRepository.save(department);
    }
}
