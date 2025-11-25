package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.dto.Loan;
import com.example.libraryspringcore.dto.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanRepositoryTest {
    private LoanRepository loanRepository;

    @BeforeEach
    void setUp() {
        loanRepository = new LoanRepository();
    }

    @Test
    void addAndFindLoan() {
        Book book = new Book();
        book.setId(1L);

        Reader reader = new Reader();
        reader.setId(2L);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());

        loanRepository.add(loan);

        List<Loan> byBook = loanRepository.findByBook(1L);
        assertEquals(1, byBook.size());

        List<Loan> byReader = loanRepository.findByReader(2L);
        assertEquals(1, byReader.size());
    }

    @Test
    void deleteLoan() {
        Loan loan = new Loan();
        loanRepository.add(loan);

        assertTrue(loanRepository.delete(loan));
        assertTrue(loanRepository.findByBook(null).isEmpty());
    }

}