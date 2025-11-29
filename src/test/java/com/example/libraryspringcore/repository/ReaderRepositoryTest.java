package com.example.libraryspringcore.repository;

import com.example.libraryspringcore.dto.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReaderRepositoryTest {
    private ReaderRepository readerRepository;

    @BeforeEach
    void setUp() {
        readerRepository = new ReaderRepository();
    }

    @Test
    void saveAndFindReader() {
        Reader reader = new Reader();
        reader.setId(1L);
        reader.setName("John");

        readerRepository.save(reader);

        Optional<Reader> found = readerRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getName());
    }

    @Test
    void saveDuplicateReader() {
        Reader reader = new Reader();
        reader.setId(1L);

        readerRepository.save(reader);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> readerRepository.save(reader));
        assertEquals("Читатель уже сохранен в базе", exception.getMessage());
    }

    @Test
    void deleteReader() {
        Reader reader = new Reader();
        reader.setId(1L);
        readerRepository.save(reader);

        assertTrue(readerRepository.delete(reader));
        assertTrue(readerRepository.findById(1L).isEmpty());
    }

    @Test
    void deleteNonExistentReader() {
        Reader reader = new Reader();
        reader.setId(1L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> readerRepository.delete(reader));
        assertEquals("Читатель не найлен", exception.getMessage());
    }

}