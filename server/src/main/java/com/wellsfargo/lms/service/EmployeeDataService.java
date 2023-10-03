package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;

import java.util.List;

public interface EmployeeDataService {
    String addEmployee(Employee empDto);

    List<Employee> getAllEmployees();

    String deleteEmployee(String empId);
}
