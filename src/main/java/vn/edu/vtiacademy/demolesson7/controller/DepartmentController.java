package vn.edu.vtiacademy.demolesson7.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public Page<Department> findAll(@ModelAttribute DepartmentFilter filter, Pageable pageable) {
        return departmentService.findAll(filter, pageable);
    }

    @PostMapping
    public Department createDepartment(@RequestBody DepartmentReq req) {
        var department = toModel(req);
        return departmentService.createDepartment(department);
    }

    @GetMapping("{id}")
    public Department findById(@PathVariable Long id) {
        var resp = departmentService.findById(id);
        log.info("Found department: {}", resp.getId());
        return resp;
    }

    @PatchMapping("{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentReq req) {

        return departmentService.updateDepartment(id, toModel(req));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    private Department toModel(DepartmentReq req) {
        return Department.builder()
                .name(req.name())
                .description(req.description())
                .build();
    }
}
