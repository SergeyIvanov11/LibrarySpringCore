package com.example.libraryspringcore.service;

import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.dto.Loan;
import com.example.libraryspringcore.dto.Reader;
import com.example.libraryspringcore.repository.BookRepository;
import com.example.libraryspringcore.repository.LoanRepository;
import com.example.libraryspringcore.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {
    private LoanService loanService;
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private LoanRepository loanRepository;
    private ObjectProvider<Loan> loanProvider;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        readerRepository = mock(ReaderRepository.class);
        loanRepository = mock(LoanRepository.class);
        loanProvider = mock(ObjectProvider.class);
        loanService = new LoanService(bookRepository, readerRepository, loanRepository, loanProvider);
    }

    @Test
    void loanBookCreatesLoan() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java");
        book.setAvailable(true);

        Reader reader = new Reader();
        reader.setId(2L);
        reader.setName("John");

        Loan loan = new Loan();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(readerRepository.findById(2L)).thenReturn(Optional.of(reader));
        when(loanProvider.getObject()).thenReturn(loan);

        Loan created = loanService.loanBook(1L, 2L);

        assertEquals(book, created.getBook());
        assertEquals(reader, created.getReader());
        assertFalse(book.isAvailable());

        verify(loanRepository).add(loan);
    }

    @Test
    void returnBookRestoresBook() {
        Book book = new Book();
        book.setId(1L);
        book.setAvailable(false);

        Reader reader = new Reader();
        reader.setId(2L);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());

        when(loanRepository.findByBook(1L)).thenReturn(List.of(loan));

        loanService.returnBook(1L, 2L);

        assertTrue(book.isAvailable());
        verify(loanRepository).delete(loan);
    }
}