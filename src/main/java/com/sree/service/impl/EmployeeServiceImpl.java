package com.sree.service.impl;

import com.sree.dto.EmployeeDto;
import com.sree.entity.Employee;
import com.sree.exception.ResourceNotFoundException;
import com.sree.mapper.EmployeeMapper;
import com.sree.repository.EmployeeRepository;
import com.sree.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not found with the given id"+ id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeDto> employeeDtos  = new ArrayList<EmployeeDto>();
//        for(Employee employee:employees) {
//            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(employee));
//        }
      //  return employeeDtos;
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmployee) {

        Employee employee=employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Emp not found")
                );
        //EmployeeDto employeeDto= EmployeeMapper.mapToEmployeeDto(employee);
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmp= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmp);
    }

    @Override
    public void deleteEmployee(Long id) {
         employeeRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Emp not found"));
        employeeRepository.deleteById(id);
    }
}
