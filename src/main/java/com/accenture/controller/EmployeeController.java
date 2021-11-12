package com.accenture.controller;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import com.accenture.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

//    @PostMapping("/create")
//    public Employee createEmployee(@RequestBody @Valid Employee employee){
//        return employeeService.createEmployee(employee);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
//        return employeeService.createEmployee(employee);
        try{
            employeeService.createEmployee(employee);
            return  new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch(EmployeeCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/all")
    public List<Employee> getAllStudents(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/studentsByName/{employeeid}")
    public List<Employee> studentsByEmployeeid(@PathVariable int employeeid){
        return employeeService.getEmployeeidByEmployeeid(employeeid);
    }

    @DeleteMapping("/delete/{employeeid}")
    public int deleteStudentByName(@PathVariable int employeeid){
        return   employeeService.deleteEmployeebyId(employeeid);
    }


    @PutMapping("/update")
    public Employee updateStudent(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }



}
