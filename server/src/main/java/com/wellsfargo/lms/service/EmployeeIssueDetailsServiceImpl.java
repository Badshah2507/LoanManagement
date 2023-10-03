package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.EmployeeIssueDetails;
import com.wellsfargo.lms.repository.EmployeeIssueDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeIssueDetailsServiceImpl implements EmployeeIssueDetailsService{
    @Autowired
    EmployeeIssueDetailsRepository employeeIssueDetailsRepository;
    @Override
    public List<EmployeeIssueDetails> getAllEmpIssues(String empId) {
        try {
            return employeeIssueDetailsRepository.findAllByEmployeeId(empId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
