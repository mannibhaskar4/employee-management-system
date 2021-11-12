package com.accenture.service;


import com.accenture.entity.Employee;
import com.accenture.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

//    public Employee getEmployee byId(String id){
//        return employeeRepository.findById(id).get();
//    }


    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }


    public List<Employee> getEmployeeidByEmployeeid(int name){
        return employeeRepository.findByEmployeeid(name);
    }


    public int deleteEmployeebyId(int deleteStudentByEmployeeid){
        employeeRepository.deleteEmployeeByEmployeeid(deleteStudentByEmployeeid);
        return 2;
    }


    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }





}
