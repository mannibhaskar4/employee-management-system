package com.accenture.controller;


import com.accenture.entity.Employee;
import com.accenture.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
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
