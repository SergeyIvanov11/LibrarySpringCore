package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
    private BookRepository bookRepository;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        bookRepository = new BookRepository(notificationService);
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

        Mockito.verify(notificationService).notify("Сохраняем книгу с id: 1");
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