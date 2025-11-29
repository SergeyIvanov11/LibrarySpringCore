package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Loan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class LoanRepository {
    private final List<Loan> loans = new CopyOnWriteArrayList<>();

    public Loan add(Loan loan){
        loans.add(loan);
        return loan;
    }

    public boolean delete(Loan loan){
        loans.remove(loan);
        return true;
    }

    public List<Loan> findByBook(Long bookId){
        return loans.stream()
                .filter(l -> l.getBook() != null
                        && Objects.equals(l.getBook().getId(), bookId))
                .toList();
    }

    public List<Loan> findByReader(Long readerId){
        return loans.stream()
                .filter(l -> l.getReader() != null
                        && Objects.equals(l.getReader().getId(), readerId))
                .toList();
    }
}
