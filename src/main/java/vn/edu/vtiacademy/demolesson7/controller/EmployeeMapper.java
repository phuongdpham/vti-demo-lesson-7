package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.Mapper;
import vn.edu.vtiacademy.demolesson7.model.Employee;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeReq employeeReq);

    Employee toEmployee(EmployeeDeptReq employeeReq);

    EmployeeResp toEmployeeResp(Employee employee);

    Set<EmployeeResp> toEmployeeResp(Set<Employee> employees);
}
