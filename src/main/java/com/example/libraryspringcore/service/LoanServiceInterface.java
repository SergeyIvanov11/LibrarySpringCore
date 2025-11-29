package com.example.libraryspringcore.service;

import com.example.libraryspringcore.dto.Loan;

public interface LoanServiceInterface {
    Loan loanBook(Long bookId, Long readerId);
    void returnBook(Long bookId, Long readerId);
}
