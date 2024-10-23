package com.sree.mapper;

import com.sree.dto.EmployeeDto;
import com.sree.entity.Employee;

public class EmployeeMapper
{
    //to map employee entity to employeeDto and viceversa
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
