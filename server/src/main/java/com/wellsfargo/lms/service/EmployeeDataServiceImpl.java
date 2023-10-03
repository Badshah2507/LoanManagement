package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeDataServiceImpl implements EmployeeDataService{

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public String addEmployee(Employee empDto) {
        try {
            employeeRepository.save(empDto);
            return "Employee was Added";
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try
        {
            return employeeRepository.findAll();
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteEmployee(String empId) {
        try
        {
            employeeRepository.deleteById(empId);
            return "Employee was deleted!";
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
}
