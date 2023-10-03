package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.model.EmployeeIssueDetails;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.repository.EmployeeIssueDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeIssueDetailsServiceImplTest {

    @Autowired
    EmployeeIssueDetailsService issueDetailsService;
    @MockBean
    EmployeeIssueDetailsRepository employeeIssueDetailsRepository;
    Employee employee;
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

        item = Item.builder()
                .itemId("I0001")
                .itemCategory("Furniture")
                .itemDescription("Tea Table")
                .itemMake("Wooden")
                .itemValuation(5000)
                .build();
    }

    @Test
    void getAllEmpIssues() {
        EmployeeIssueDetails obj1 = EmployeeIssueDetails.builder()
                .issueId(1L)
                .employee(employee)
                .issueDate(new Date(24092023))
                .item(item)
                .returnDate(new Date(24092024))
                .build();
        List<EmployeeIssueDetails> result = new ArrayList<>();
        result.add(obj1);
        when(employeeIssueDetailsRepository.findAllByEmployeeId(any(String.class))).thenReturn(result);

        List<EmployeeIssueDetails> response = issueDetailsService.getAllEmpIssues("E0001");
        assertEquals(1L, response.get(0).getIssueId());
        assertEquals(1L, response.get(0).getEmployee().getEmployeeId());
        assertEquals("Technology", response.get(0).getEmployee().getDepartment());
        assertEquals("Prathamesh", response.get(0).getEmployee().getEmployeeName());
        assertEquals("Male", response.get(0).getEmployee().getGender());
        assertEquals("Associate", response.get(0).getEmployee().getDesignation());
        assertEquals("Wooden", response.get(0).getItem().getItemMake());
        assertEquals("I0001", response.get(0).getItem().getItemId());
        assertEquals("Tea Table", response.get(0).getItem().getItemDescription());
        assertEquals("Furniture", response.get(0).getItem().getItemCategory());
        assertEquals(5000, response.get(0).getItem().getItemValuation());
    }
}