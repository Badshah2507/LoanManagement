package com.wellsfargo.lms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LoanCardTest {
    /**
     * Method under test: {@link LoanCard#toString()}
     */
    @Test
    void testToString() {
        LoanCard loanCard = new LoanCard();
        loanCard.setEmployeeCards(new ArrayList<>());
        loanCard.setLoanDurationYrs(1);
        loanCard.setLoanId("42");
        loanCard.setLoanType("Loan Type");
        assertEquals("LoanCard{loanId='42', loanType='Loan Type', loanDurationYrs=1}", loanCard.toString());
    }
}

