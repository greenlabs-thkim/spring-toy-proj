package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal save(Goal goal);
    Optional<Goal> findById(long id);
    List<Goal> findByName(String name);
    List<Goal> findByStatus(short status);
    List<Goal> findByStatusAndName(short status, String name);
    List<Goal> findAll();
}
