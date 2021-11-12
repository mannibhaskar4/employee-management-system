package com.accenture.service;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements  EmployeeServiceInterface{

    @Autowired
    EmployeeRepository employeeRepository;

//    public Employee createEmployee(Employee employee){
//        return employeeRepository.save(employee);
//    }

    @Override
    public void createEmployee(Employee employee)throws ConstraintViolationException,EmployeeCollectionException{
//        return employeeRepository.save(employee);
        Optional<Employee> employeeOptional=employeeRepository.findByEmployee(employee.getName() );
        if(employeeOptional.isPresent()){
            throw new EmployeeCollectionException(EmployeeCollectionException.EmployeeAlreadyExists());

        }else{
//            employee.set(new Date(System.currentTimeMillis()));
            employeeRepository.save(employee);
        }
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
