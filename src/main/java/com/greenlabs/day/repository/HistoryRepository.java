package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface HistoryRepository extends CrudRepository<History, Long> {
    // List<History> findAllByUser(User user);
    // List<History> findAllByGoal(Goal goal);
    Optional<History> findByEntryAndEndTimeIsNull(Entry entry);
    List<History> findAllByEntry(Entry entry);
}
