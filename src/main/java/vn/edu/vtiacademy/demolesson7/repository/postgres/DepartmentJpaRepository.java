package vn.edu.vtiacademy.demolesson7.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.edu.vtiacademy.demolesson7.model.Department;

import java.util.Optional;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
    Optional<Department> findByName(String name);
    Optional<Department> findByDescription(String name);

    boolean existsByName(String name);

    @Query("SELECT d FROM Department d WHERE LOWER(d.name) = LOWER(:name)") // JPQL
    Optional<Department> findByNameIgnoreCase(String name);

    @Query(value = "select * from departments d where lower(d.name) = lower(:name)", nativeQuery = true) // native
    Optional<Department> findByNameIgnoreCaseNative(String name);
}
