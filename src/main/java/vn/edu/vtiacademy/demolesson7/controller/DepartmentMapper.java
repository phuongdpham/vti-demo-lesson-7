package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.edu.vtiacademy.demolesson7.model.Department;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface DepartmentMapper {
    DepartmentResp toDepartmentResp(Department department);

    Department toDepartment(DepartmentReq departmentReq);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartment(DepartmentUpdateReq departmentUpdateReq, @MappingTarget Department department);
}
