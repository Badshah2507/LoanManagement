package com.wellsfargo.lms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EmployeeTest {
    /**
     * Method under test: {@link Employee#toString()}
     */
    @Test
    void testToString() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Employee.dateOfBirth
        //     Employee.dateOfJoining
        //     Employee.department
        //     Employee.designation
        //     Employee.employeeCards
        //     Employee.employeeId
        //     Employee.employeeIssueDetails
        //     Employee.employeeName
        //     Employee.gender

        Employee employee = new Employee();
        employee.setDateOfBirth(null);
        employee.setDateOfJoining(null);
        employee.setDepartment("Department");
        employee.setDesignation("Designation");
        employee.setEmployeeCards(new ArrayList<>());
        employee.setEmployeeId(1L);
        employee.setEmployeeIssueDetails(new ArrayList<>());
        employee.setEmployeeName("Employee Name");
        employee.setGender("Gender");

        assertEquals(
                "Employee{employeeId='1', employeeName='Employee Name', designation='Designation', department='Department',"
                        + " gender='Gender', dateOfBirth=null, dateOfJoining=null}",
                employee.toString());
    }
}

