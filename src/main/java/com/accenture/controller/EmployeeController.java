package com.accenture.controller;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import com.accenture.service.EmployeeService;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;


//@CrossOrigin("*")
// --- * means it can be accesed by every front end framework such as angular or react
@CrossOrigin(origins = {"http://localhost:3000/"})
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

//    @PostMapping("/create")
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
//        return employeeService.createEmployee(employee);
        try{

            Employee emp=employeeService.createEmployee(employee);
            return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch(EmployeeCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }catch(MongoWriteException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//            return new ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("April Fool's Status Code (CODE 418)\n");
        }

    }

//    @GetMapping("/all")
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employeeByName/{employeeid}")
    public ResponseEntity<?> employeeByEmployeeid(@PathVariable int employeeid){
        try{
            Employee employee=employeeService.getEmployeeByEmployeeid(employeeid);
            return new ResponseEntity<Employee>(employee,HttpStatus.OK);
        }catch (EmployeeCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/delete/{employeeid}")
    public ResponseEntity<?> deleteEmployeeByEmployeeid(@PathVariable int employeeid){
        try{
            Employee employee=employeeService.deleteEmployeebyId(employeeid);
            return new ResponseEntity<Employee>(employee,HttpStatus.OK);
        }catch (EmployeeCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        try{

            Employee emp=employeeService.updateEmployee(employee);
            return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
//        return employeeService.updateEmployee(employee);
    }


    @PutMapping("/update/{employeeid}")
    public ResponseEntity<?> updateEmployeeByEmployeeId(@PathVariable int employeeid,@RequestBody Employee employee){
        Employee updateEmployee = null;
        try {
            updateEmployee = employeeRepository.findByEmployeeId(employeeid)
                    .orElseThrow(() -> new EmployeeCollectionException("Employee id "+employeeid+" does not Exist."));
            updateEmployee.setName(employee.getName());
            updateEmployee.setDob(employee.getDob());
            updateEmployee.setDoj(employee.getDoj());
            updateEmployee.setSalary(employee.getSalary());
        } catch (EmployeeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }catch (NullPointerException e){
            return new ResponseEntity<>("Employee id does not exsists",HttpStatus.OK);
        }
//
//        updateEmployee.setName(employee.getName());
//        updateEmployee.setDob(employee.getDob());
//        updateEmployee.setDoj(employee.getDoj());
//        updateEmployee.setSalary(employee.getSalary());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);

    }


}
