package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.EmployeeCard;

import java.util.List;

public interface EmployeeCardService {
    List<EmployeeCard> getEmployeeCard(String empId);
}
