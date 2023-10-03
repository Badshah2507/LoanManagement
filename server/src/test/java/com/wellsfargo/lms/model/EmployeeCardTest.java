package com.wellsfargo.lms.model;

import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EmployeeCardTest {
    /**
     * Method under test: {@link EmployeeCard#toString()}
     */
    @Test
    void testToString() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     EmployeeCard.EmployeeCardId
        //     EmployeeCard.cardIssueDate
        //     EmployeeCard.employee
        //     EmployeeCard.loanCard

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

        LoanCard loanCard = new LoanCard();
        loanCard.setEmployeeCards(new ArrayList<>());
        loanCard.setLoanDurationYrs(1);
        loanCard.setLoanId("42");
        loanCard.setLoanType("Loan Type");

        EmployeeCard employeeCard = new EmployeeCard();
        employeeCard.setCardIssueDate(mock(Date.class));
        employeeCard.setEmployee(employee);
        employeeCard.setLoanCard(loanCard);
        employeeCard.toString();
    }
}

