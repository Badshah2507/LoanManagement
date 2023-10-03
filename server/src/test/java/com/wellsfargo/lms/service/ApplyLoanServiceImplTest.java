package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplyLoanServiceImplTest {

    @Autowired
    ApplyLoanService applyLoanService;
    @MockBean
    EmployeeCardRepository employeeCardRepository;
    @MockBean
    EmployeeIssueDetailsRepository employeeIssueDetailsRepository;
    @MockBean
    EmployeeRepository employeeRepository;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    LoanCardRepository loanCardRepository;
    Employee employee;
    LoanCard loanCard;
    Item item;

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

        item = Item.builder()
                .itemId("I0001")
                .itemCategory("Furniture")
                .itemDescription("Tea Table")
                .itemMake("Wooden")
                .itemValuation(5000)
                .build();
    }

    @Test
    void saveApplyLoanData() {
        Map<String, String> body = new HashMap<>();
        body.put("employeeId","E0069");
        body.put("itemCategory","Furniture");
        body.put("itemDescription","Tea Table");
        body.put("itemValuation","5000");
        body.put("itemMake","Wooden");

        when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(employee));
        when(itemRepository.findByItemCategoryAndItemDescription(anyString(), anyString())).thenReturn(item);
        when((loanCardRepository.findByLoanType(anyString()))).thenReturn(loanCard);

        String response = applyLoanService.saveApplyLoanData(body);
        assertEquals("Loan Applied Successfully", response);
    }
}