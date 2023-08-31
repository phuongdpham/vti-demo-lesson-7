package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.Mapper;
import vn.edu.vtiacademy.demolesson7.model.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResp toDepartmentResp(Department department);

    Department toDepartment(DepartmentReq departmentReq);
}
