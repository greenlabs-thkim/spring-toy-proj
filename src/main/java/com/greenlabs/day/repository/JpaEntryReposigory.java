package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaEntryReposigory extends CrudRepository<Entry, Long>, EntryRepository {
    @Override
    Entry save(Entry entry);

    @Override
    Optional<Entry> findById(Long id);

    @Override
    List<Entry> findAllByUserId(Long userId);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Entry> findByUserIdAndGoalId(Long userId, Long goalId);
}
