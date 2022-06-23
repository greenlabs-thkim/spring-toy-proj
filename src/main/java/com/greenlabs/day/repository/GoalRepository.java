package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goal save(Goal goal);
    Optional<Goal> findById(Long id);
    List<Goal> findAllByName(String name);
    List<Goal> findAllByStatus(short status);
    List<Goal> findAllByStatusAndName(short status, String name);
    List<Goal> findAll();
}
