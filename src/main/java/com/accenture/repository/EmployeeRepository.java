package com.accenture.repository;

import com.accenture.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    List<Employee> findByEmployeeid(int employeeid);
//
    int deleteEmployeeByEmployeeid(int employeeid);

}
