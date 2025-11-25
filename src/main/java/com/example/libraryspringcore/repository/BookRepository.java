package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.dto.Reader;
import com.example.libraryspringcore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
@RequiredArgsConstructor
@Repository
public class BookRepository {
    private final List<Book> books = new CopyOnWriteArrayList<>();
    private final NotificationService notificationService;

    public Book save(Book book) {
        if(findById(book.getId()).isPresent()){
            throw new IllegalStateException("Книга уже сохранена в базе");
        }
        books.add(book);
        notificationService.notify("Сохраняем книгу с id: " + book.getId());
        return book;
    }

    public Optional<Book> findById(Long id) {
        notificationService.notify("Ищем книгу с id: " + id);
        return books.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst();
    }

    public boolean delete(Book book){
        if(!findById(book.getId()).isPresent()){
            throw new IllegalStateException("Книга не найдена");
        }
        books.remove(book);
        return true;
    }
}
