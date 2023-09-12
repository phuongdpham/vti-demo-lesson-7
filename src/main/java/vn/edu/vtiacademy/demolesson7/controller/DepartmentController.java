package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.vtiacademy.demolesson7.controller.validation.AddressInstanceMapper;
import vn.edu.vtiacademy.demolesson7.service.DepartmentService;

import java.security.Principal;

@RestController
@RequestMapping("/departments")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
public class DepartmentController {
    DepartmentService departmentService;
    DepartmentMapper mapper;
    EmployeeMapper employeeMapper;

    @GetMapping
    public Page<DepartmentResp> findAll(@ModelAttribute @ParameterObject DepartmentFilter filter,
                                        @ParameterObject Pageable pageable) {
        log.info("Find all departments with filter: {}, pageable: {}", filter, pageable);
        return departmentService.findAll(filter, pageable)
                .map(mapper::toDepartmentResp);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public DepartmentResp createDepartment(@RequestBody @Valid DepartmentReq req) {
        return req.transform(mapper::toDepartment)
                .transform(departmentService::createDepartment)
                .transform(mapper::toDepartmentResp);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public DepartmentResp findById(@PathVariable Long id, Principal principal) {
        log.info("Find department by id: {}, username: {}", id, principal.getName());

        return departmentService.findById(id)
                .transform(resp -> {
                    log.info("Found department: {}", resp);
                    return resp;
                })
                .transform(mapper::toDepartmentResp);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public DepartmentResp updateDepartment(@PathVariable @Positive Long id, @RequestBody DepartmentUpdateReq req) {
        var existingDepartment = departmentService.findById(id);

        mapper.updateDepartment(req, existingDepartment);

        return existingDepartment.transform(departmentService::save)
                .transform(mapper::toDepartmentResp);

//        return departmentService.updateDepartment(id, mapper.toDepartment(req));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("{id}/addresses")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public DepartmentResp addAddress(@PathVariable Long id, @RequestBody @Valid AddressReq req) {
        return departmentService.addAddress(id, AddressInstanceMapper.INSTANCE.toAddress(req))
                .transform(mapper::toDepartmentResp);
    }

    @PostMapping("{id}/employees")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public DepartmentResp addEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDeptReq req) {
        return departmentService.addEmployee(id, employeeMapper.toEmployee(req))
                .transform(mapper::toDepartmentResp);
    }
}
