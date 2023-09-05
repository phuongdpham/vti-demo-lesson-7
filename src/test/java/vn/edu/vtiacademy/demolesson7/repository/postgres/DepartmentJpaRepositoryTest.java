package vn.edu.vtiacademy.demolesson7.repository.postgres;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentJpaRepositoryTest {
    @Autowired
    DepartmentJpaRepository departmentJpaRepository;

    @Test
    void givenExistName_thenFindByNameIgnoreCase_willPresent() {
        var expectedName = "Elsa Toy";
        var department = departmentJpaRepository.findByNameIgnoreCase(expectedName);

        assertTrue(department.isPresent());

        assertEquals(expectedName, department.get().getName());
    }

    @Test
    void givenNotExistName_thenFindByNameIgnoreCase_willReturnEmpty() {
        var expectedName = "unknown";
        var department = departmentJpaRepository.findByNameIgnoreCase(expectedName);

        assertTrue(department.isEmpty());
    }

    // Unit test
    // Integration test
    // UAT user acceptance test
    @Test
    void givenExistName_thenFindByNameIgnoreCaseNative_willIsPresent() {
        var expectedName = "Elsa Toy";
        var department = departmentJpaRepository.findByNameIgnoreCaseNative(expectedName);

        assertTrue(department.isPresent());

        assertEquals(expectedName, department.get().getName());
    }

    @Test
    void givenNotExistName_thenFindByNameIgnoreCaseNative_willReturnEmpty() {
        var expectedName = "unknown";
        var department = departmentJpaRepository.findByNameIgnoreCaseNative(expectedName);

        assertTrue(department.isEmpty());
    }
}