package com.accenture.repository;

import com.accenture.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    List<Employee> findByEmployeeid(int employeeid);
//
    int deleteEmployeeByEmployeeid(int employeeid);

    @Query("{'name': ?0}")
    Optional<Employee> findByEmployee(String name);

}
