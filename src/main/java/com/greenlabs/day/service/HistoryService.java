package com.greenlabs.day.service;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.History;
import com.greenlabs.day.repository.EntryRepository;
import com.greenlabs.day.repository.HistoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HistoryService {
    private final HistoryRepository historyRepository;
    private final EntryRepository entryRepository;

    public HistoryService(HistoryRepository historyRepository, EntryRepository entryRepository) {
        this.historyRepository = historyRepository;
        this.entryRepository = entryRepository;
    }

    public Long start(Long entryId) {
        Entry entry = getEntryById(entryId);

        if (historyRepository.findByEntryAndEndTimeIsNull(entry).isPresent()) {
            throw new IllegalStateException("이미 시작한 목표가 있습니다.");
        }

        return historyRepository.save(new History(entry)).getId();
    }

    public void finish(Long historyId) {
        Optional<History> optionalHistory = historyRepository.findById(historyId);
        if (optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            if (history.getEndTime() == null) {
                history.setEndTime(LocalDateTime.now());
            }
        }
    }


    public List<History> listByEntry(Long entryId) {
        Entry entry = getEntryById(entryId);

        return historyRepository.findAllByEntry(entry);
    }

    private Entry getEntryById(Long id) {
        Optional<Entry> optionalEntry = entryRepository.findById(id);
        if (!optionalEntry.isPresent()) {
            throw new IllegalStateException("유효하지 않은 가입정보 입니다.");
        }
        return optionalEntry.get();
    }
}
