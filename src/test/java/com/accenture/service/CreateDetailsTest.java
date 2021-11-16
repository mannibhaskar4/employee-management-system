package com.accenture.service;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CreateDetailsTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    public void sholudCreateEmployeeTest()throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee em1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        employeeRepository=Mockito.mock(EmployeeRepository.class);
        Employee em2=employeeService.createEmployee(em1);
//        Mockito.doReturn(employeeRepository.save(em1));

        employeeRepository.save(em1);
        System.out.println(employeeRepository.findAll());
//        assertThat(em2.equals(em1));

//        Assertions.assertTrue(em2.equals(em1));

    }

}
