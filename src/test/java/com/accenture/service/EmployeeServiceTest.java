package com.accenture.service;

import com.accenture.entity.Employee;
import com.accenture.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeServiceTest {

    Employee emp1;
    Employee emp2;
    Employee emp3;
    List<Employee> list;

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @BeforeAll
    public void setup(){

        Date d3 = new Date(1999, 1, 3);
        Date d4 = new Date(2021, 1, 3);

        list = new ArrayList<Employee>();

        emp1= new Employee("101","manni",d3,d4,13000.2,"mab",101);
        emp2= new Employee("102","Bhaskar",d3,d4,13000.2,"mab",102);
        list.add(emp1);
        list.add(emp2);
    }



//    @Test
//    public void shouldCreateEmployeeTest(){
//        employeeService.createEmployee(emp1);
//        employeeService.createEmployee(emp2);
//        Assertions.assertTrue(employeeService.getAllEmployee().isEmpty());
////        System.out.println(employeeService.getAllEmployee().isEmpty());
//
//    }
//
//    @Test
//    public void shouldNotCreateEmployeeTest(){
////        employeeService.createEmployee(emp1);
////        employeeService.createEmployee(emp2);
//        Assertions.assertFalse(employeeService.getAllEmployee().isEmpty());
////        System.out.println(employeeService.getAllEmployee());
////        List<Employee> l2=employeeService.getAllEmployee();
////        for(Employee i: l2)
////        System.out.println(i);
//    }




}
