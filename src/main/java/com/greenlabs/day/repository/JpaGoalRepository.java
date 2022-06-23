package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Goal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaGoalRepository extends CrudRepository<Goal, Long>, GoalRepository {
    @Override
    Goal save(Goal goal);

    @Override
    Optional<Goal> findById(long id);

    @Override
    List<Goal> findAllByName(String name);

    @Override
    List<Goal> findAllByStatus(short status);

    @Override
    List<Goal> findAllByStatusAndName(short status, String name);

    @Override
    List<Goal> findAll();
}
