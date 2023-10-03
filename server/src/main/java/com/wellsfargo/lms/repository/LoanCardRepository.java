package com.wellsfargo.lms.repository;

import com.wellsfargo.lms.model.LoanCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LoanCardRepository extends JpaRepository<LoanCard, String> {
    LoanCard findByLoanType(String itemCategory);
}

