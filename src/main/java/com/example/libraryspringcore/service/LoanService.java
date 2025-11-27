package com.example.libraryspringcore.service;

import com.example.libraryspringcore.config.NotifyInLog;
import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.dto.Loan;
import com.example.libraryspringcore.dto.Reader;
import com.example.libraryspringcore.repository.BookRepository;
import com.example.libraryspringcore.repository.LoanRepository;
import com.example.libraryspringcore.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final LoanRepository loanRepository;
    private final ObjectProvider<Loan> loanProvider; // для получения прототипа

    @NotifyInLog("Заем с книгой {0} и читателем {1} создан")
    public Loan loanBook(Long bookId, Long readerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Книга не найдена с id: " + bookId));
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalStateException("Читатель не найден с id: " + readerId));

        Loan loan = loanProvider.getObject();   // получение прототипа
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());

        loanRepository.add(loan);
        book.setAvailable(false);
        return loan;
    }

    @NotifyInLog("Книга c id {0} возвращена читателем с id {1}")
    public void returnBook(Long bookId, Long readerId) {
        List<Loan> loans = loanRepository.findByBook(bookId);

        Loan target = loans.stream()
                .filter(l -> Objects.equals(l.getReader().getId(), readerId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Заем не найден для книги с id " + bookId + " и читателя с id " + readerId));

        Book book = target.getBook();
        book.setAvailable(true);
        loanRepository.delete(target);
    }

}
