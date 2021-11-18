package com.accenture.service;


import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.accenture.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private static final int EMPLOYEE_ID=145272;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

//    @BeforeAll
//    public void setUp() {
//        employeeService = new ();
//
//        personRepositoryMock = mock(PersonRepository.class);
//        personService.setPersonRepository(personRepositoryMock);
//    }

    @BeforeEach
    void srtUp()throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sholudCreateEmployeeTest()throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee passing=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
//        Employee em2 = null;
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Employee actual=employeeRepository.save(passing);
        Employee expected=employeeService.createEmployee(passing);
//        System.out.println(expected.getName());
        assertEmployee(expected,actual);
        assertEquals(expected,actual);

    }

    @Test
    public void createEmployeeShouldThrowEmployeeidAlreadyExists() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee passing1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee passing2=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP129",145279);
        Optional<Employee> returned = null;
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        Employee actual=employeeService.createEmployee(passing1);

        when(employeeRepository.findByEmployeeId(passing2.getEmployeeid()))
                .thenReturn(Optional.of(passing2));


        try {
            Employee em=employeeService.createEmployee(passing2);
        }catch (EmployeeCollectionException e){
              Assertions.assertEquals("Employee with given Employee Id already exists",e.getMessage());
//            System.out.println(e.getMessage());
        }


    }


    @Test
    public void createEmployeeShouldThrowEnterpriseidAlreadyExists() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee passing1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee passing2=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145277);
        Optional<Employee> returned = null;
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        Employee actual=employeeService.createEmployee(passing1);

        when(employeeRepository.findByEnterpriseid(passing2.getEnterpriseid()))
                .thenReturn(Optional.of(passing2));


        try {
            Employee em=employeeService.createEmployee(passing2);
        }catch (EmployeeCollectionException e){
            Assertions.assertEquals("Employee with given Enterprise Id already exists",e.getMessage());
//            System.out.println(e.getMessage());
        }


    }


    @Test
    public void shouldGetAllEmployees() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee data1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee data2=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145272);


        List<Employee> expected=new ArrayList<Employee>();
//        Page page=new PageImpl(expected);

        expected.add(data1);
        expected.add(data2);

        when(employeeRepository.findAll()).thenReturn(expected);
        expected=employeeService.getAllEmployee();
        List<Employee> actual=new ArrayList<Employee>();

        actual=employeeRepository.findAll();
//        System.out.println(actual.get(0).getName());
        if((expected.size()==actual.size())){
            for(int i=0;i<expected.size();i++){
                assertEmployee(expected.get(i),actual.get(i));
            }
        }
        else
            fail("Test fails");
//        Assertions.assertEquals();


    }

    @Test
    public void shouldGetEmployeeidByEmployeeid() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee data1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee data2=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145272);
        Employee data3=new Employee("102","Mallik",d1,d2,52000.2,"EMZ123",145274);
        List<Employee> expected=new ArrayList<Employee>();
        expected.add(data1);
        expected.add(data2);
        expected.add(data3);

        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        Employee dataInserted1=employeeService.createEmployee(data1);
        Employee dataInserted2=employeeService.createEmployee(data2);
        Employee dataInserted3=employeeService.createEmployee(data3);


        when(employeeRepository.findByEmployeeId(EMPLOYEE_ID)).thenReturn(
                Optional.of(dataInserted2)
        );
        System.out.println(employeeRepository.findByEmployeeid(EMPLOYEE_ID).get(0).getName()+" "+employeeService.getEmployeeidByEmployeeid(EMPLOYEE_ID).get(0).getName());

    }

    private void assertEmployee(Employee expected,Employee actual){
//        System.out.println("test performed");
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getSalary(),actual.getSalary());
        assertEquals(expected.getDob(),actual.getDob());
        assertEquals(expected.getDoj(),actual.getDoj());
        assertEquals(expected.getEnterpriseid(),actual.getEnterpriseid());
        assertEquals(expected.getEmployeeid(),actual.getEmployeeid());
    }



}
