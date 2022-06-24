package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Goal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends CrudRepository<Goal, Long> {
    List<Goal> findAllByName(String name);

    List<Goal> findAllByStatus(short status);

    List<Goal> findAllByStatusAndName(short status, String name);

    @Override
    List<Goal> findAll();
}
