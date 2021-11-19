package com.accenture.exception;

public class EmployeeCollectionException extends Exception{

    private static final long serialVersionUID=1L;

    public EmployeeCollectionException(String message){
        super(message);
    }

    public static String NotFoundException(int employeeid){
        return "Employee with "+employeeid+" not found!";
    }

    public static String EmployeeAlreadyExists(){
        return "Employee with given name already exists";
    }

    public static String EnterpriseidAlreadyExists(){return "Employee with given Enterprise Id already exists";}

    public static String EmployeeidAlreadyExists(){return "Employee with given Employee Id already exists";}

}
