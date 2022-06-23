package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryRepository {
    Entry save(Entry entry);
    Optional<Entry> findById(Long id);
    List<Entry> findAllByUserId(Long userId);

    Optional<Entry> findByUserIdAndGoalId(Long userId, Long goalId);
    void deleteById(Long id);
}
