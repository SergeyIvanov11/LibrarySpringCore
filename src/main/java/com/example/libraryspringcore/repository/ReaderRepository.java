package com.example.libraryspringcore.repository;


import com.example.libraryspringcore.config.NotifyInLog;
import com.example.libraryspringcore.dto.Reader;
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
    @NotifyInLog("Сохраняем читателя {0}")
    public Reader save(Reader reader) {
        if(findById(reader.getId()).isPresent()){
            throw new IllegalStateException("Читатель уже сохранен в базе");
        }
        readers.add(reader);
        return reader;
    }

    @NotifyInLog("Ищем читателя с id: {0}")
    public Optional<Reader> findById(Long id) {
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
