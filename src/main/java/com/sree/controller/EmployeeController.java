package com.sree.controller;

import com.sree.dto.EmployeeDto;
import com.sree.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController// it will make our class a controller class which can handle http requests
@AllArgsConstructor
@RequestMapping("/api/employees")//this is the base url
@CrossOrigin("*")// all the clients can call employee related rest api

public class EmployeeController {

    @Autowired// to create bean
   private EmployeeService employeeService;

   @PostMapping
   //RequestBody annotation will convert the json object that we received to employeedto object
   public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);

       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
   }

   ///Build getEmployee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
       EmployeeDto employeeDto = employeeService.getEmployeeById(id);
       return ResponseEntity.ok(employeeDto);
    }

    //Build get all employees RESTAPI
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
       return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable("id")  long id,@RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    //to bind uri template varaible value to the parameter we use @Pathvariable
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
       employeeService.deleteEmployee(id);
       return ResponseEntity.ok("Employee deleted successfully!!");
    }

}
