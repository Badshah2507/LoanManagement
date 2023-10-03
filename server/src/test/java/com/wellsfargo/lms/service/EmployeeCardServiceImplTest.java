package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.model.EmployeeCard;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.repository.EmployeeCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeCardServiceImplTest {

    @Autowired
    EmployeeCardService employeeCardService;
    @MockBean
    EmployeeCardRepository employeeCardRepository;
    Employee employee;
    LoanCard loanCard;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .employeeId(1L)
                .dateOfBirth(new Date(25072001))
                .dateOfJoining(new Date(30012023))
                .department("Technology")
                .designation("Associate")
                .employeeName("Prathamesh")
                .gender("Male")
                .build();

        loanCard = LoanCard.builder()
                .loanId("L0001")
                .loanType("Furniture")
                .loanDurationYrs(5)
                .build();
    }

    @Test
    void getEmployeeCard() {
        EmployeeCard employeeCard = EmployeeCard.builder()
                .EmployeeCardId(1L)
                .employee(employee)
                .loanCard(loanCard)
                .cardIssueDate(new Date(24092023))
                .build();
        List<EmployeeCard> result = new ArrayList<>();
        result.add(employeeCard);
        when(employeeCardRepository.findAllByEmployeeId(anyString())).thenReturn(result);

        List<EmployeeCard> response = employeeCardService.getEmployeeCard("E0001");
        assertEquals(1L, response.get(0).getEmployeeCardId());
        assertEquals(1L, response.get(0).getEmployee().getEmployeeId());
        assertEquals("Technology", response.get(0).getEmployee().getDepartment());
        assertEquals("Prathamesh", response.get(0).getEmployee().getEmployeeName());
        assertEquals("Male", response.get(0).getEmployee().getGender());
        assertEquals("Associate", response.get(0).getEmployee().getDesignation());
        assertEquals("L0001", response.get(0).getLoanCard().getLoanId());
        assertEquals(5, response.get(0).getLoanCard().getLoanDurationYrs());
        assertEquals("Furniture", response.get(0).getLoanCard().getLoanType());
    }
}