package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.repository.LoanCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanCardDataServiceImpl implements LoanCardDataService{

    @Autowired
    LoanCardRepository loanCardRepository;

    @Override
    public String addLoanCard(LoanCard loanCard)
    {
        try
        {
            loanCardRepository.save(loanCard);
            return "Loan Card added!";
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return "Unable to add loan card!: " + e.getMessage();
        }

    }

    @Override
    public List<LoanCard> getAllLoanCards() {
        try
        {
            return loanCardRepository.findAll();
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteLoanCard(String loanId) {
        try
        {
            loanCardRepository.deleteById(loanId);
            return "Loan Card was deleted!";
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
}
