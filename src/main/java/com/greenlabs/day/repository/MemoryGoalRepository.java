package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Goal;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryGoalRepository implements GoalRepository {

    private static Map<Long, Goal> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Goal save(Goal goal) {
        if (goal.getId() == 0) {
            goal.setId(++sequence);
        }

        store.put(goal.getId(), goal);
        return goal;
    }

    @Override
    public Optional<Goal> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Goal> findByName(String name) {
        List<Goal> collect = store.values().stream()
                .filter(user -> user.getName().contentEquals(name))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Goal> findByStatus(short status) {
        List<Goal> collect = store.values().stream()
                .filter(user -> user.getStatus() == status)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Goal> findByStatusAndName(short status, String name) {
        List<Goal> collect = store.values().stream()
                .filter(user -> user.getStatus() == status)
                .filter(user -> user.getName().contentEquals(name))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Goal> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
