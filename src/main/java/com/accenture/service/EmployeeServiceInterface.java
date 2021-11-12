package com.accenture.service;

import com.accenture.entity.Employee;
import com.accenture.exception.EmployeeCollectionException;
import com.mongodb.MongoWriteException;

import javax.validation.ConstraintViolationException;

public interface EmployeeServiceInterface {


    public void createEmployee(Employee employee) throws MongoWriteException,ConstraintViolationException,EmployeeCollectionException;

}
