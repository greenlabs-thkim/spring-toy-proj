package com.greenlabs.day.service;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.repository.GoalRepository;

import java.util.List;

public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository repository) {
        goalRepository = repository;
    }

    public long regist(Goal goal) {
        goalRepository.save(goal);
        return goal.getId();
    }

    public List<Goal> search(Goal goal) {
        if(goal.getStatus() > 0 && !goal.getName().isEmpty()) {
            return goalRepository.findByStatusAndName(goal.getStatus(), goal.getName());
        } else if (goal.getStatus() > 0) {
            return goalRepository.findByStatus(goal.getStatus());
        } else if (!goal.getName().isEmpty()) {
            return goalRepository.findByName(goal.getName());
        }

        return goalRepository.findAll();
    }
}
