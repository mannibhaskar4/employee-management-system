package com.accenture.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "employee")
public class Employee {

    @Id
    private String id;

    @Field(name = "name")
    @NotNull(message = "name cannot be null")
    private String name;

    @Field(name = "dob")
    @NotNull(message = "Date of birth cannot be null")
    private Date dob;

    @Field(name = "doj")
    @NotNull(message = "Date of joining cannot be null")
    private Date doj;

    @Field(name = "salary")
    @NotNull(message = "Entering invalid salary")
    private double salary;

    @Field(name="enterpriseid")
    @NotNull(message = "Entering enterprise id")
    private String enterpriseid;


    //add validations
    @Field(name = "employeeid")
    @Min(value = 100001,message = "invalid value")
    private int employeeid;

    public Employee(String id, String name, Date dob, Date doj, double salary, String enterpriseid, int employeeid) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.doj = doj;
        this.salary = salary;
        this.enterpriseid = enterpriseid;
        this.employeeid = employeeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
}
