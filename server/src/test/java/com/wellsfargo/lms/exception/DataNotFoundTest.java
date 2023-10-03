package com.wellsfargo.lms.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.repository.EmployeeCardRepository;
import com.wellsfargo.lms.repository.EmployeeIssueDetailsRepository;
import com.wellsfargo.lms.repository.EmployeeRepository;
import com.wellsfargo.lms.repository.ItemRepository;
import com.wellsfargo.lms.repository.LoanCardRepository;
import com.wellsfargo.lms.service.ApplyLoanService;

@SpringBootTest
public class DataNotFoundTest {

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
                .dateOfBirth(new Date(02112001))
                .dateOfJoining(new Date(30012023))
                .department("Technology")
                .designation("Program Associate")
                .employeeName("Anish")
                .gender("Male")
                .build();

        loanCard = LoanCard.builder()
                .loanId("1001")
                .loanType("Electronics")
                .loanDurationYrs(5)
                .build();

        item = Item.builder()
                .itemId("10001")
                .itemCategory("iPhone")
                .itemDescription("15 Pro")
                .itemMake("Titanium")
                .itemValuation(149000)
                .build();
    }
	
	
	@Test
    void saveApplyLoanData() {
        Map<String, String> body = new HashMap<>();
        body.put("employeeId","102");
        body.put("itemCategory","Mobile Phone");
        body.put("itemDescription","15 Pro");
        body.put("itemValuation","149000");
        body.put("itemMake","Titanium");

        when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(employee));
        when(itemRepository.findByItemCategoryAndItemDescription(anyString(), anyString())).thenReturn(item);
        when((loanCardRepository.findByLoanType(anyString()))).thenReturn(null);

        Exception exception = assertThrows(DataNotFound.class, () -> {
        	applyLoanService.saveApplyLoanData(body);
        });
        
        assertEquals("No Such Item Category exists",exception.getMessage());
    }
	
}
