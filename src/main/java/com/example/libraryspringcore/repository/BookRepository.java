package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.config.NotifyInLog;
import com.example.libraryspringcore.dto.Book;
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

    @NotifyInLog("Сохраняем книгу {0}")
    public Book save(Book book) {
        if(findById(book.getId()).isPresent()){
            throw new IllegalStateException("Книга уже сохранена в базе");
        }
        books.add(book);
        return book;
    }
    @NotifyInLog("Ищем книгу с id: {0}")
    public Optional<Book> findById(Long id) {
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
