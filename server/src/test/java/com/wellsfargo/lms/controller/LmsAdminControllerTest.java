package com.wellsfargo.lms.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.model.Item;
import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.service.EmployeeDataService;
import com.wellsfargo.lms.service.ItemDataService;
import com.wellsfargo.lms.service.LoanCardDataService;
import com.wellsfargo.lms.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LmsAdminController.class})
@ExtendWith(SpringExtension.class)
class LmsAdminControllerTest {
    @MockBean
    private EmployeeDataService employeeDataService;

    @MockBean
    private ItemDataService itemDataService;

    @Autowired
    private LmsAdminController lmsAdminController;

    @MockBean
    private LoanCardDataService loanCardDataService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link LmsAdminController#saveUser(User)}
     */
    @Test
    void testSaveUser() throws Exception {
        when(userService.saveUser(Mockito.<User>any())).thenReturn("Save User");

        User user = new User();
        user.setEmployeeId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save User"));
    }

    /**
     * Method under test: {@link LmsAdminController#login(User)}
     */
    @Test
    void testLogin() throws Exception {
        when(userService.login(Mockito.<User>any())).thenReturn(new HashMap<>());

        User user = new User();
        user.setEmployeeId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link LmsAdminController#addEmployee(Employee)}
     */
    @Test
    void testAddEmployee() throws Exception {
        when(employeeDataService.addEmployee(Mockito.<Employee>any())).thenReturn("Add Employee");
        Date dateOfBirth = mock(Date.class);
        when(dateOfBirth.getTime()).thenReturn(10L);
        Date dateOfJoining = mock(Date.class);
        when(dateOfJoining.getTime()).thenReturn(10L);

        Employee employee = new Employee();
        employee.setDateOfBirth(dateOfBirth);
        employee.setDateOfJoining(dateOfJoining);
        employee.setDepartment("Department");
        employee.setDesignation("Designation");
        employee.setEmployeeCards(new ArrayList<>());
        employee.setEmployeeId(1L);
        employee.setEmployeeIssueDetails(new ArrayList<>());
        employee.setEmployeeName("Employee Name");
        employee.setGender("Gender");
        String content = (new ObjectMapper()).writeValueAsString(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Employee"));
    }

    /**
     * Method under test: {@link LmsAdminController#addEmployee(Employee)}
     */
    @Test
    void testAddEmployee2() throws Exception {
        when(employeeDataService.addEmployee(Mockito.<Employee>any())).thenReturn(null);
        Date dateOfBirth = mock(Date.class);
        when(dateOfBirth.getTime()).thenReturn(10L);
        Date dateOfJoining = mock(Date.class);
        when(dateOfJoining.getTime()).thenReturn(10L);

        Employee employee = new Employee();
        employee.setDateOfBirth(dateOfBirth);
        employee.setDateOfJoining(dateOfJoining);
        employee.setDepartment("Department");
        employee.setDesignation("Designation");
        employee.setEmployeeCards(new ArrayList<>());
        employee.setEmployeeId(1L);
        employee.setEmployeeIssueDetails(new ArrayList<>());
        employee.setEmployeeName("Employee Name");
        employee.setGender("Gender");
        String content = (new ObjectMapper()).writeValueAsString(employee);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    /**
     * Method under test: {@link LmsAdminController#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeDataService.getAllEmployees()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getAllEmployees");
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsAdminController#deleteEmployee(String)}
     */
    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeDataService.deleteEmployee(Mockito.<String>any())).thenReturn("Delete Employee");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/admin/deleteEmployee")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Employee"));
    }

    /**
     * Method under test: {@link LmsAdminController#getAllItems()}
     */
    @Test
    void testGetAllItems() throws Exception {
        when(itemDataService.getAllItems()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getAllItems");
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsAdminController#getAllLoanCards()}
     */
    @Test
    void testGetAllLoanCards() throws Exception {
        when(loanCardDataService.getAllLoanCards()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getAllLoanCards");
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsAdminController#addItem(Item)}
     */
    @Test
    void testAddItem() throws Exception {
        when(itemDataService.addItemData(Mockito.<Item>any())).thenReturn("Add Item Data");

        Item item = new Item();
        item.setEmployeeIssueDetails(new ArrayList<>());
        item.setIssueStatus("Issue Status");
        item.setItemCategory("Item Category");
        item.setItemDescription("Item Description");
        item.setItemId("42");
        item.setItemMake("Item Make");
        item.setItemValuation(42);
        String content = (new ObjectMapper()).writeValueAsString(item);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Item Data"));
    }

    /**
     * Method under test: {@link LmsAdminController#addLoanCardDetails(LoanCard)}
     */
    @Test
    void testAddLoanCardDetails() throws Exception {
        when(loanCardDataService.addLoanCard(Mockito.<LoanCard>any())).thenReturn("Add Loan Card");

        LoanCard loanCard = new LoanCard();
        loanCard.setEmployeeCards(new ArrayList<>());
        loanCard.setLoanDurationYrs(1);
        loanCard.setLoanId("42");
        loanCard.setLoanType("Loan Type");
        String content = (new ObjectMapper()).writeValueAsString(loanCard);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addLoanCardDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Loan Card"));
    }

    /**
     * Method under test: {@link LmsAdminController#deleteItem(String)}
     */
    @Test
    void testDeleteItem() throws Exception {
        when(itemDataService.deleteItemData(Mockito.<String>any())).thenReturn("Delete Item Data");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/admin/deleteItem")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Item Data"));
    }

    /**
     * Method under test: {@link LmsAdminController#welcomeMessage()}
     */
    @Test
    void testWelcomeMessage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/");
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to LMS"));
    }

    /**
     * Method under test: {@link LmsAdminController#welcomeMessage()}
     */
    @Test
    void testWelcomeMessage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(lmsAdminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to LMS"));
    }
}

