package com.prinpedia.backend.dao;

import com.prinpedia.backend.entity.Entry;

import java.util.Optional;

public interface EntryDao {
    Optional<Entry> findByTitle(String title);
    Boolean create(Entry entry);
    Boolean update(Entry entry);
}
