package com.wellsfargo.lms.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.lms.repository.ItemRepository;
import com.wellsfargo.lms.service.ApplyLoanService;
import com.wellsfargo.lms.service.EmployeeCardService;
import com.wellsfargo.lms.service.EmployeeIssueDetailsService;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LmsUserController.class})
@ExtendWith(SpringExtension.class)
class LmsUserControllerTest {
    @MockBean
    private ApplyLoanService applyLoanService;

    @MockBean
    private EmployeeCardService employeeCardService;

    @MockBean
    private EmployeeIssueDetailsService employeeIssueDetailsService;

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private LmsUserController lmsUserController;

    /**
     * Method under test: {@link LmsUserController#applyLoan(Map)}
     */
    @Test
    void testApplyLoan() throws Exception {
        when(applyLoanService.saveApplyLoanData(Mockito.<Map<String, String>>any())).thenReturn("Save Apply Loan Data");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/user/applyLoan")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new HashMap<>()));
        MockMvcBuilders.standaloneSetup(lmsUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save Apply Loan Data"));
    }

    /**
     * Method under test: {@link LmsUserController#getItemDesc(String)}
     */
    @Test
    void testGetItemDesc() throws Exception {
        when(itemRepository.getItemDesc(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getItemDesc")
                .param("itemCat", "foo");
        MockMvcBuilders.standaloneSetup(lmsUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsUserController#getEmpIssues(String)}
     */
    @Test
    void testGetEmpIssues() throws Exception {
        when(employeeIssueDetailsService.getAllEmpIssues(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getEmplIssues")
                .param("emplId", "foo");
        MockMvcBuilders.standaloneSetup(lmsUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsUserController#getEmployeeCards(String)}
     */
    @Test
    void testGetEmployeeCards() throws Exception {
        when(employeeCardService.getEmployeeCard(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getEmployeeCard")
                .param("emplId", "foo");
        MockMvcBuilders.standaloneSetup(lmsUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LmsUserController#getitemCat()}
     */
    @Test
    void testGetitemCat() throws Exception {
        when(itemRepository.getItemCat()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getItemCat");
        MockMvcBuilders.standaloneSetup(lmsUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

