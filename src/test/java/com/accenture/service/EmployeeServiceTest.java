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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.*;
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
        Employee expected=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
//        Employee em2 = null;
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenAnswer(i -> i.getArguments()[0]);

//        Employee actual=employeeRepository.save(passing);
        Employee actual=employeeService.createEmployee(expected);
//        System.out.println(expected.getName());
        assertEmployee(expected,actual);
        assertEquals(expected,actual);

    }

    @Test
    public void createEmployeeShouldThrowEmployeeidAlreadyExists() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
//        Employee passing1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee expected=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP129",145279);
        Optional<Employee> returned = null;
//        when(employeeRepository.save(Mockito.any(Employee.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
//        Employee actual=employeeService.createEmployee(passing1);

        when(employeeRepository.findByEmployeeId(expected.getEmployeeid()))
                .thenReturn(Optional.of(expected));


//        try {
//            Employee actual=employeeService.createEmployee(expected);
//        }catch (EmployeeCollectionException e){
//              Assertions.assertEquals("Employee with given Employee Id already exists",e.getMessage());
////            System.out.println(e.getMessage());
//        }


        EmployeeCollectionException exception=Assertions.assertThrows(EmployeeCollectionException.class,()->{
            Employee actual=employeeService.createEmployee(expected);
        });
        assertTrue(exception.getMessage().contains("Employee with given Employee Id already exists"));

    }


    @Test
    public void createEmployeeShouldThrowEnterpriseidAlreadyExists() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
//        Employee passing1=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
        Employee expected=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145277);
//        Optional<Employee> returned = null;
//        when(employeeRepository.save(Mockito.any(Employee.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
//        Employee actual=employeeService.createEmployee(passing1);

        when(employeeRepository.findByEnterpriseid(expected.getEnterpriseid()))
                .thenReturn(Optional.of(expected));


//        try {
//            Employee em=employeeService.createEmployee(passing2);
//        }catch (EmployeeCollectionException e){
//            Assertions.assertEquals("Employee with given Enterprise Id already exists",e.getMessage());
////            System.out.println(e.getMessage());
//        }


        EmployeeCollectionException exception=Assertions.assertThrows(EmployeeCollectionException.class,()->{
            Employee actual=employeeService.createEmployee(expected);
        });
        assertTrue(exception.getMessage().contains("Employee with given Enterprise Id already exists"));


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
        List<Employee> added=employeeService.getAllEmployee();
        List<Employee> actual=new ArrayList<Employee>();

        actual=employeeRepository.findAll();
//        System.out.println(actual.get(0).getName());
        if((added.size()==actual.size())){
            for(int i=0;i<expected.size();i++){
                assertEmployee(added.get(i),actual.get(i));
            }
        }
        else
            fail("Test fails");
//        Assertions.assertEquals();


    }

    @Test
    public void shouldGetEmployeeByEmployeeid() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee expected=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145272);


        when(employeeRepository.findByEmployeeid(EMPLOYEE_ID)).thenReturn(expected);
        Employee actual=employeeService.getEmployeeByEmployeeid(EMPLOYEE_ID);

        assertEmployee(expected,actual);
        assertEquals(expected,actual);


//        when(employeeRepository.save(Mockito.any(Employee.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
//        Employee dataInserted1=employeeService.createEmployee(data1);
//        Employee dataInserted2=employeeService.createEmployee(data2);
//        Employee dataInserted3=employeeService.createEmployee(data3);
//
//

//        System.out.println(employeeRepository.findByEmployeeid(EMPLOYEE_ID).get(0).getName()+" "+employeeService.getEmployeeidByEmployeeid(EMPLOYEE_ID).get(0).getName());




    }

    @Test
    public void shouldGetByEmployeeidThrowsCustomNotFoundException() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee expected=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145272);


        when(employeeRepository.findByEmployeeid(EMPLOYEE_ID)).thenReturn(null);





//        when(employeeRepository.save(Mockito.any(Employee.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
//        Employee dataInserted1=employeeService.createEmployee(data1);
//        Employee dataInserted2=employeeService.createEmployee(data2);
//        Employee dataInserted3=employeeService.createEmployee(data3);
//
//

//        System.out.println(employeeRepository.findByEmployeeid(EMPLOYEE_ID).get(0).getName()+" "+employeeService.getEmployeeidByEmployeeid(EMPLOYEE_ID).get(0).getName());

        EmployeeCollectionException exception=Assertions.assertThrows(EmployeeCollectionException.class,()->{
            Employee actual=employeeService.getEmployeeByEmployeeid(EMPLOYEE_ID);
        });
        assertTrue(exception.getMessage().contains("Employee with "+EMPLOYEE_ID+" not found!"));



    }

    @Test
    public void shouldDeleteEmployeebyId() throws EmployeeCollectionException{
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee excepted=new Employee("101","Manni",d1,d2,52000.2,"EMP123",EMPLOYEE_ID);
        when(employeeRepository.deleteEmployeeByEmployeeid(EMPLOYEE_ID)).thenReturn(excepted);

        Employee actual = employeeService.deleteEmployeebyId(EMPLOYEE_ID);

        assertEmployee(excepted,actual);
        assertEquals(excepted,actual);


//        List<Employee> expected=new ArrayList<Employee>();
//
//        expected.add(data1);
//
//        when(employeeRepository.deleteEmployeeByEmployeeid(EMPLOYEE_ID)).thenReturn(expected);
//
//        List<Employee> added=employeeService.deleteEmployeebyId(EMPLOYEE_ID);
//        List<Employee> actual=new ArrayList<Employee>();
////
//        actual=employeeRepository.deleteEmployeeByEmployeeid(EMPLOYEE_ID);
//        System.out.println(actual.get(0).getName());
//        if((added.size()==actual.size())){
//            for(int i=0;i<expected.size();i++){
//                assertEmployee(added.get(i),actual.get(i));
//            }
//        }
//        else
//            fail("Test fails");

    }

    @Test
    public void shouldDeleteEmployeeidThrowsCustomNotFoundExceptionBy() throws EmployeeCollectionException {
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee expected=new Employee("105","Bhaskar",d1,d2,52000.2,"EMP123",145272);


        when(employeeRepository.deleteEmployeeByEmployeeid(EMPLOYEE_ID)).thenReturn(null);





//        when(employeeRepository.save(Mockito.any(Employee.class)))
//                .thenAnswer(i -> i.getArguments()[0]);
//        Employee dataInserted1=employeeService.createEmployee(data1);
//        Employee dataInserted2=employeeService.createEmployee(data2);
//        Employee dataInserted3=employeeService.createEmployee(data3);
//
//

//        System.out.println(employeeRepository.findByEmployeeid(EMPLOYEE_ID).get(0).getName()+" "+employeeService.getEmployeeidByEmployeeid(EMPLOYEE_ID).get(0).getName());

        EmployeeCollectionException exception=Assertions.assertThrows(EmployeeCollectionException.class,()->{
            Employee actual=employeeService.deleteEmployeebyId(EMPLOYEE_ID);
        });
        assertTrue(exception.getMessage().contains("Employee with "+EMPLOYEE_ID+" not found!"));



    }

    @Test
    public void shouldUpdateEmployee(){
        Date d1=new Date(1999,12,11);
        Date d2=new Date(2021,12,11);
        Employee expected=new Employee("101","Manni",d1,d2,52000.2,"EMP123",145279);
//        Employee em2 = null;
        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(expected);

//        Employee actual=employeeRepository.save(passing);
        Employee actual=employeeService.updateEmployee(expected);
//        System.out.println(expected.getName());
        assertEmployee(expected,actual);
        assertEquals(expected,actual);

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
