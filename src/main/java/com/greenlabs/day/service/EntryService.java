package com.greenlabs.day.service;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.EntryRepository;
import com.greenlabs.day.repository.GoalRepository;
import com.greenlabs.day.repository.UserRepository;
import com.greenlabs.day.repository.partial.IGoal;
import com.greenlabs.day.repository.partial.IUser;

import java.util.List;
import java.util.Optional;

public class EntryService {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    public EntryService(EntryRepository entryRepository, UserRepository userRepository, GoalRepository goalRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
    }

    public Entry entry(Long userId, Long goalId) {
        User user = getUserById(userId);
        Goal goal = getGoalById(goalId);

        validateEntry(user, goal);
        return entry(user, goal);
    }

    public Entry entry(User user, Goal goal) {
        validateEntry(user, goal);
        return entryRepository.save(new Entry(user, goal));
    }

    public List<IGoal> listGoalByUser(User user) {
        return entryRepository.findAllByUser(user);
    }

    public List<IUser> listUserByGoal(Goal goal) {
        return entryRepository.findAllByGoal(goal);
    }


    private User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new IllegalStateException("유효하지 않은 사용자입니다.");

        }
        return optionalUser.get();
    }

    private  Goal getGoalById(Long goalId) {
        Optional<Goal> optionalGoal = goalRepository.findById(goalId);
        if (!optionalGoal.isPresent()) {
            throw new IllegalStateException("유효하지 않은 목표입니다.");
        }
        return optionalGoal.get();
    }


    private void validateEntry(User user, Goal goal) {
        if (entryRepository.findByUserAndGoal(user, goal).isPresent()) {
            throw new IllegalStateException("이미 가입된 목표그룹 입니다.");
        }
    }
}
