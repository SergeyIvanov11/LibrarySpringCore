package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    void saveAndFindBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setAvailable(true);

        bookRepository.save(book);

        Optional<Book> found = bookRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Title", found.get().getTitle());
    }

    @Test
    void saveDuplicateBookThrowsException() {
        Book book = new Book();
        book.setId(1L);

        bookRepository.save(book);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bookRepository.save(book));
        assertEquals("Книга уже сохранена в базе", exception.getMessage());
    }

    @Test
    void deleteBook() {
        Book book = new Book();
        book.setId(1L);
        bookRepository.save(book);

        assertTrue(bookRepository.delete(book));
        assertTrue(bookRepository.findById(1L).isEmpty());
    }

    @Test
    void deleteNonExistentBookThrowsException() {
        Book book = new Book();
        book.setId(1L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> bookRepository.delete(book));
        assertEquals("Книга не найдена", exception.getMessage());
    }
}