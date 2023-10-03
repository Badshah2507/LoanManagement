package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.EmployeeIssueDetails;

import java.util.List;

public interface EmployeeIssueDetailsService {
    List<EmployeeIssueDetails> getAllEmpIssues(String empId);
}
