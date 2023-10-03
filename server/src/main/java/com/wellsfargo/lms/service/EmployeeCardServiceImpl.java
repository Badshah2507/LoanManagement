package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.EmployeeCard;
import com.wellsfargo.lms.repository.EmployeeCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeCardServiceImpl implements EmployeeCardService{
    @Autowired
    EmployeeCardRepository employeeCardRepository;
    @Override
    public List<EmployeeCard> getEmployeeCard(String empId) {
        try {
            return employeeCardRepository.findAllByEmployeeId(empId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
