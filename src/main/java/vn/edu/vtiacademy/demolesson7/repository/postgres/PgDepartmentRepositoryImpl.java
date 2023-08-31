package vn.edu.vtiacademy.demolesson7.repository.postgres;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Department;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PgDepartmentRepositoryImpl implements DepartmentRepository {
    DepartmentJpaRepository repository;

    @Override
    public Page<Department> findAll(DepartmentFilter filter, Pageable pageable) {
        var spec = DepartmentSpecification.withFilter(filter);
        return repository.findAll(spec, pageable);
    }

    @Override
    public Department save(Department department) {
        return repository.save(department);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Department> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
