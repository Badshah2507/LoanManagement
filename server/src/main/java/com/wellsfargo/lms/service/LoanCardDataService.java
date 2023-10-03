package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.LoanCard;

import java.util.List;

public interface LoanCardDataService {
    String addLoanCard(LoanCard loanCardDto);
    List<LoanCard> getAllLoanCards();
    String deleteLoanCard(String loanId);
}
