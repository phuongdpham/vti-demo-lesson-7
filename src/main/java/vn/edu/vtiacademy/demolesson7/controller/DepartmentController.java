package vn.edu.vtiacademy.demolesson7.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.vtiacademy.demolesson7.model.Department;
import vn.edu.vtiacademy.demolesson7.service.DepartmentService;

@RestController
@RequestMapping("/departments")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    DepartmentService departmentService;
    DepartmentMapper mapper;
    AddressMapper addressMapper;
    EmployeeMapper employeeMapper;

    @GetMapping
    public Page<DepartmentResp> findAll(@ModelAttribute DepartmentFilter filter,
                                        Pageable pageable) {
        return departmentService.findAll(filter, pageable)
                .map(mapper::toDepartmentResp);
    }

    @PostMapping
    public DepartmentResp createDepartment(@RequestBody @Valid DepartmentReq req) {
        return req.transform(mapper::toDepartment)
                .transform(departmentService::createDepartment)
                .transform(mapper::toDepartmentResp);
//        var department = mapper.toDepartment(req);
//
//        var result = departmentService.createDepartment(department);
//
//        return mapper.toDepartmentResp(result);
    }

    @GetMapping("{id}")
    public DepartmentResp findById(@PathVariable Long id) {
        return departmentService.findById(id)
                .transform(resp -> {
                    log.info("Found department: {}", resp.getId());
                    return resp;
                })
                .transform(mapper::toDepartmentResp);

//        var resp = departmentService.findById(id);
//        log.info("Found department: {}", resp.getId());
//        return mapper.toDepartmentResp(resp);
    }

    @PatchMapping("{id}")
    public DepartmentResp updateDepartment(@PathVariable Long id, @RequestBody DepartmentReq req) {
        return mapper.toDepartment(req)
                .transform(department -> departmentService.updateDepartment(id, department))
                .transform(mapper::toDepartmentResp);

//        return departmentService.updateDepartment(id, mapper.toDepartment(req));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("{id}/addresses")
    public DepartmentResp addAddress(@PathVariable Long id, @RequestBody @Valid AddressReq req) {
        return departmentService.addAddress(id, addressMapper.toAddress(req))
                .transform(mapper::toDepartmentResp);
    }

    @PostMapping("{id}/employees")
    public DepartmentResp addEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDeptReq req) {
        return departmentService.addEmployee(id, employeeMapper.toEmployee(req))
                .transform(mapper::toDepartmentResp);
    }
}
