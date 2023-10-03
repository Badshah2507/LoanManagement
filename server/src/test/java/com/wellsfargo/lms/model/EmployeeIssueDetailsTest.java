package com.wellsfargo.lms.model;

import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EmployeeIssueDetailsTest {
    /**
     * Method under test: {@link EmployeeIssueDetails#toString()}
     */
    @Test
    void testToString() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     EmployeeIssueDetails.employee
        //     EmployeeIssueDetails.issueDate
        //     EmployeeIssueDetails.issueId
        //     EmployeeIssueDetails.item
        //     EmployeeIssueDetails.returnDate

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

        Item item = new Item();
        item.setEmployeeIssueDetails(new ArrayList<>());
        item.setIssueStatus("Issue Status");
        item.setItemCategory("Item Category");
        item.setItemDescription("Item Description");
        item.setItemId("42");
        item.setItemMake("Item Make");
        item.setItemValuation(42);

        EmployeeIssueDetails employeeIssueDetails = new EmployeeIssueDetails();
        employeeIssueDetails.setEmployee(employee);
        employeeIssueDetails.setIssueDate(mock(Date.class));
        employeeIssueDetails.setIssueId(1L);
        employeeIssueDetails.setItem(item);
        employeeIssueDetails.setReturnDate(mock(Date.class));
        employeeIssueDetails.toString();
    }
}

