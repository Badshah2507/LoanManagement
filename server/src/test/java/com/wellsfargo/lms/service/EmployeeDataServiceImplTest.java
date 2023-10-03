package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.Employee;
import com.wellsfargo.lms.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeDataServiceImplTest {

    @Autowired
    private EmployeeDataServiceImpl employeeDataServiceImpl;

    @Autowired
    EmployeeDataService employeeDataService;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    void addEmployee() {
        Employee employee = Employee.builder()
                .employeeId(1L)
                .dateOfBirth(new Date(25072001))
                .dateOfJoining(new Date(30012023))
                .department("Technology")
                .designation("Associate")
                .employeeName("Prathamesh")
                .gender("Male")
                .build();

        String response = employeeDataService.addEmployee(employee);
        assertEquals("Employee was Added", response);
    }

    /**
     * Method under test: {@link EmployeeDataServiceImpl#addEmployee(Employee)}
     */
    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setDateOfBirth(mock(Date.class));
        employee.setDateOfJoining(mock(Date.class));
        employee.setDepartment("Department");
        employee.setDesignation("Designation");
        employee.setEmployeeCards(new ArrayList<>());
        employee.setEmployeeId(1L);
        employee.setEmployeeIssueDetails(new ArrayList<>());
        employee.setEmployeeName("Employee Name");
        employee.setGender("Gender");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee);

        Employee empDto = new Employee();
        empDto.setDateOfBirth(mock(Date.class));
        empDto.setDateOfJoining(mock(Date.class));
        empDto.setDepartment("Department");
        empDto.setDesignation("Designation");
        empDto.setEmployeeCards(new ArrayList<>());
        empDto.setEmployeeId(1L);
        empDto.setEmployeeIssueDetails(new ArrayList<>());
        empDto.setEmployeeName("Employee Name");
        empDto.setGender("Gender");
        assertEquals("Employee was Added", employeeDataServiceImpl.addEmployee(empDto));
        verify(employeeRepository).save(Mockito.<Employee>any());
    }

    @Test
    void getAllEmployees() {
        Employee employee1 = Employee.builder()
                .employeeId(1L)
                .dateOfBirth(new Date(25072001))
                .dateOfJoining(new Date(30012023))
                .department("Technology")
                .designation("Associate")
                .employeeName("Prathamesh")
                .gender("Male")
                .build();

        Employee employee2 = Employee.builder()
                .employeeId(2L)
                .dateOfBirth(new Date(25072001))
                .dateOfJoining(new Date(30012023))
                .department("HR")
                .designation("Manager")
                .employeeName("Om")
                .gender("Male")
                .build();
        List<Employee> result = new ArrayList<>();
        result.add(employee1);
        result.add(employee2);
        when(employeeRepository.findAll()).thenReturn(result);

        List<Employee> response = employeeDataService.getAllEmployees();
        assertEquals(1L, response.get(0).getEmployeeId());
        assertEquals(2L, response.get(1).getEmployeeId());
        assertEquals("Technology", response.get(0).getDepartment());
        assertEquals("HR", response.get(1).getDepartment());
        assertEquals("Associate", response.get(0).getDesignation());
        assertEquals("Manager", response.get(1).getDesignation());
        assertEquals("Prathamesh", response.get(0).getEmployeeName());
        assertEquals("Om", response.get(1).getEmployeeName());
        assertEquals("Male", response.get(0).getGender());
        assertEquals("Male", response.get(1).getGender());
    }

    /**
     * Method under test: {@link EmployeeDataServiceImpl#getAllEmployees()}
     */
    @Test
    void testGetAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualAllEmployees = employeeDataServiceImpl.getAllEmployees();
        assertSame(employeeList, actualAllEmployees);
        assertTrue(actualAllEmployees.isEmpty());
        verify(employeeRepository).findAll();
    }

    /**
     * Method under test: {@link EmployeeDataServiceImpl#deleteEmployee(String)}
     */
    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(Mockito.<String>any());
        assertEquals("Employee was deleted!", employeeDataServiceImpl.deleteEmployee("42"));
        verify(employeeRepository).deleteById(Mockito.<String>any());
    }
}