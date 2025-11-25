package com.example.libraryspringcore.repository;


import com.example.libraryspringcore.dto.Loan;
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
public class ReaderRepository {
    private final List<Reader> readers = new CopyOnWriteArrayList<>();
    private final NotificationService notificationService;

    public Reader save(Reader reader) {
        if(findById(reader.getId()).isPresent()){
            throw new IllegalStateException("Читатель уже сохранен в базе");
        }
        readers.add(reader);
        notificationService.notify("Сохраняем читателя с id: " + reader.getId());
        return reader;
    }

    public Optional<Reader> findById(Long id) {
        notificationService.notify("Ищем читателя с id: " + id);
        return readers.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst();
    }

    public boolean delete(Reader reader){
        if(!findById(reader.getId()).isPresent()){
            throw new IllegalStateException("Читатель не найлен");
        }
        readers.remove(reader);
        return true;
    }
}
