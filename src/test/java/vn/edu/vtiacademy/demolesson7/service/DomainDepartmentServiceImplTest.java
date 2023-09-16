package vn.edu.vtiacademy.demolesson7.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import vn.edu.vtiacademy.demolesson7.controller.DepartmentFilter;
import vn.edu.vtiacademy.demolesson7.model.Department;
import vn.edu.vtiacademy.demolesson7.model.DepartmentNotFoundException;
import vn.edu.vtiacademy.demolesson7.model.ErrorCode;
import vn.edu.vtiacademy.demolesson7.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DomainDepartmentServiceImplTest {
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DomainDepartmentServiceImpl departmentService;

    @Test
    void givenEmptyFilterAndDefaultPageable_thenFindAll_willReturnPageOfDepartments() {
        var filter = new DepartmentFilter(null, null, null, null, null, null, null);
        var department = Department.builder()
                .id(1L)
                .build();
        var pageable = Pageable.unpaged();

        given(departmentRepository.findAll(filter, pageable))
                .willReturn(new PageImpl<>(List.of(department)));

        var resp = departmentService.findAll(filter, pageable);

        assertEquals(1, resp.getTotalElements());

        verify(departmentRepository, times(1)).findAll(filter, pageable);

        var deptResp = resp.getContent().get(0);

        assertEquals(department.getId(), deptResp.getId());
    }

    @Test
    void givenDepartmentUpdateReq_whenUpdateDepartmentPartialFields_willUpdateFieldsNotNull() {
        var department = Department.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        var departmentUpdateReq = Department.builder()
                .name("name2")
                .build();

        given(departmentRepository.findById(department.getId()))
                .willReturn(Optional.of(department));

        departmentService.updateDepartment(department.getId(), departmentUpdateReq);

        var departmentCaptor = ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository, times(1)).save(departmentCaptor.capture());
        verify(departmentRepository, times(1)).findById(department.getId());

        var departmentUpdated = departmentCaptor.getValue();
        assertEquals(department.getId(), departmentUpdated.getId());
        assertEquals(departmentUpdateReq.getName(), departmentUpdated.getName());
        assertEquals(department.getDescription(), departmentUpdated.getDescription());
    }

    @Test
    void givenExistsDepartment_whenFindById_willReturn() {
        var department = Department.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();

        given(departmentRepository.findById(department.getId()))
                .willReturn(Optional.of(department));

        var resp = departmentService.findById(department.getId());

        assertEquals(department.getId(), resp.getId());
        assertEquals(department.getName(), resp.getName());
        assertEquals(department.getDescription(), resp.getDescription());
    }

    @Test
    void givenNotExistDepartmentId_whenFindById_willThrowDepartmentNotFoundException() {
        var departmentId = 1L;

        given(departmentRepository.findById(departmentId))
                .willReturn(Optional.empty());

        var ex = assertThrows(DepartmentNotFoundException.class, () -> departmentService.findById(departmentId));

        assertEquals(ErrorCode.DEPARTMENT_NOT_FOUND, ex.getErrorCode());
    }

}