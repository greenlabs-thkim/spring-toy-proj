package com.greenlabs.day.service;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.repository.EntryRepository;

import java.util.Optional;

public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public void entry(Long userId, Long goalId) {
        validateEntry(userId, goalId);
        entryRepository.save(new Entry(userId, goalId));
    }

    private void validateEntry(Long userId, Long goalId) {
        if (userId == null || userId <= 0) {
            throw new IllegalStateException("사용자 id 가 지정되지 않았습니다.");
        }

        if (goalId == null || goalId <= 0) {
            throw new IllegalStateException("목표 id 가 지정되지 않았습니다.");
        }

        Optional<Entry> entry = entryRepository.findByUserIdAndGoalId(userId, goalId);
        if(entry.isPresent()) {
            throw new IllegalStateException("이미 가입된 목표그룹 입니다.");
        }
    }
}
