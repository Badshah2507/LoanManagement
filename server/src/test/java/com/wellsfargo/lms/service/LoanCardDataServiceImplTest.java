package com.wellsfargo.lms.service;

import com.wellsfargo.lms.model.LoanCard;
import com.wellsfargo.lms.model.User;
import com.wellsfargo.lms.repository.LoanCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoanCardDataServiceImplTest {

    @Autowired
    private LoanCardDataServiceImpl loanCardDataServiceImpl;

    @Autowired
    LoanCardDataService loanCardDataService;
    @MockBean
    LoanCardRepository loanCardRepository;

    LoanCard loanCard;

    @BeforeEach
    void setUp() {
        loanCard = new LoanCard();
    }

    @AfterEach
    void tearDown() {
        loanCard = null;
    }

    @Test
    void addLoanCard() {
        loanCard = LoanCard.builder()
                .loanId("L0001")
                .loanType("Furniture")
                .loanDurationYrs(5)
                .build();

        String response = loanCardDataService.addLoanCard(loanCard);
        assertEquals("Loan Card added!", response);
    }

    /**
     * Method under test: {@link LoanCardDataServiceImpl#addLoanCard(LoanCard)}
     */
    @Test
    void testAddLoanCard() {
        LoanCard loanCard = new LoanCard();
        loanCard.setEmployeeCards(new ArrayList<>());
        loanCard.setLoanDurationYrs(1);
        loanCard.setLoanId("42");
        loanCard.setLoanType("Loan Type");
        when(loanCardRepository.save(Mockito.<LoanCard>any())).thenReturn(loanCard);

        LoanCard loanCard2 = new LoanCard();
        loanCard2.setEmployeeCards(new ArrayList<>());
        loanCard2.setLoanDurationYrs(1);
        loanCard2.setLoanId("42");
        loanCard2.setLoanType("Loan Type");
        assertEquals("Loan Card added!", loanCardDataServiceImpl.addLoanCard(loanCard2));
        verify(loanCardRepository).save(Mockito.<LoanCard>any());
    }

    @Test
    void getAllLoanCards() {
        loanCard = LoanCard.builder()
                .loanId("L0001")
                .loanType("Furniture")
                .loanDurationYrs(5)
                .build();

        LoanCard loanCard2 = LoanCard.builder()
                .loanId("L0002")
                .loanType("Stationery")
                .loanDurationYrs(2)
                .build();

        LoanCard loanCard3 = LoanCard.builder()
                .loanId("L0003")
                .loanType("Crockery")
                .loanDurationYrs(3)
                .build();

        List<LoanCard> result = new ArrayList<>();
        result.add(loanCard);
        result.add(loanCard2);
        result.add(loanCard3);

        when(loanCardRepository.findAll()).thenReturn(result);

        List<LoanCard> response = loanCardDataService.getAllLoanCards();
        assertEquals("L0001", response.get(0).getLoanId());
        assertEquals("L0002", response.get(1).getLoanId());
        assertEquals("L0003", response.get(2).getLoanId());
        assertEquals("Furniture", response.get(0).getLoanType());
        assertEquals("Stationery", response.get(1).getLoanType());
        assertEquals("Crockery", response.get(2).getLoanType());
        assertEquals(5, response.get(0).getLoanDurationYrs());
        assertEquals(2, response.get(1).getLoanDurationYrs());
        assertEquals(3, response.get(2).getLoanDurationYrs());
    }

    /**
     * Method under test: {@link LoanCardDataServiceImpl#getAllLoanCards()}
     */
    @Test
    void testGetAllLoanCards() {
        ArrayList<LoanCard> loanCardList = new ArrayList<>();
        when(loanCardRepository.findAll()).thenReturn(loanCardList);
        List<LoanCard> actualAllLoanCards = loanCardDataServiceImpl.getAllLoanCards();
        assertSame(loanCardList, actualAllLoanCards);
        assertTrue(actualAllLoanCards.isEmpty());
        verify(loanCardRepository).findAll();
    }

    /**
     * Method under test: {@link LoanCardDataServiceImpl#deleteLoanCard(String)}
     */
    @Test
    void testDeleteLoanCard() {
        doNothing().when(loanCardRepository).deleteById(Mockito.<String>any());
        assertEquals("Loan Card was deleted!", loanCardDataServiceImpl.deleteLoanCard("42"));
        verify(loanCardRepository).deleteById(Mockito.<String>any());
    }
}