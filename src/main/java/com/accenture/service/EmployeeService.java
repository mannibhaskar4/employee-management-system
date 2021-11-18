package com.accenture.service;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
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
    public Employee createEmployee(Employee employee)throws MongoWriteException,ConstraintViolationException,EmployeeCollectionException{
//        return employeeRepository.save(employee);
//        Optional<Employee> employeeOptional=employeeRepository.findByEmployee(employee.getName() );
        Optional<Employee> employeeOptional2=employeeRepository.findByEnterpriseid(employee.getEnterpriseid());
        Optional<Employee> employeeOptional3=employeeRepository.findByEmployeeId(employee.getEmployeeid());
        if(/*employeeOptional.isPresent()||*/employeeOptional2.isPresent()||employeeOptional3.isPresent()){
//            if(employeeOptional.isPresent())
//                throw new EmployeeCollectionException(EmployeeCollectionException.EmployeeAlreadyExists());

            if(employeeOptional3.isPresent())
                throw new EmployeeCollectionException(EmployeeCollectionException.EmployeeidAlreadyExists());

            if(employeeOptional2.isPresent())
                throw new EmployeeCollectionException(EmployeeCollectionException.EnterpriseidAlreadyExists());
        }else{
//            employee.set(new Date(System.currentTimeMillis()));

            Employee emp=employeeRepository.save(employee);
            return emp;
        }
        return null;
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


    public List<Employee> deleteEmployeebyId(int deleteStudentByEmployeeid){
        return employeeRepository.deleteEmployeeByEmployeeid(deleteStudentByEmployeeid);
//        return ;
    }


    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }





}
