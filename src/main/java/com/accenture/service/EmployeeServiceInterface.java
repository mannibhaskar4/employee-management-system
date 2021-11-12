package com.accenture.service;

import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;

import javax.validation.ConstraintViolationException;

public interface EmployeeServiceInterface {


    public void createEmployee(Employee employee) throws ConstraintViolationException,EmployeeCollectionException;

}
