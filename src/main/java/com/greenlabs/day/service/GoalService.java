package com.greenlabs.day.service;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.repository.GoalRepository;

import java.util.List;

public class GoalService {
    private final GoalRepository goalRepository;


    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public long regist(Goal goal) {
        goalRepository.save(goal);
        return goal.getId();
    }

    public List<Goal> search(Goal goal) {
        if (goal != null) {
            if (goal.getStatus() > 0 && goal.getName() != null && !goal.getName().isEmpty()) {
                return goalRepository.findAllByStatusAndName(goal.getStatus(), goal.getName());
            } else if (goal.getStatus() > 0) {
                return goalRepository.findAllByStatus(goal.getStatus());
            } else if (goal.getName() != null && !goal.getName().isEmpty()) {
                return goalRepository.findAllByName(goal.getName());
            }
        }

        return goalRepository.findAll();
    }
}
