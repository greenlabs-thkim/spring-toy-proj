package com.greenlabs.day.service;


import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.domain.History;
import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.GoalRepository;
import com.greenlabs.day.repository.HistoryQueryDslRepository;
import com.greenlabs.day.repository.HistoryRepository;
import com.greenlabs.day.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class HistoryServiceTest {
    private final HistoryService historyService;
    private final HistoryRepository historyRepository;
    private final HistoryQueryDslRepository historyDetailRepository;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final EntryService entryService;


    @Autowired
    public HistoryServiceTest(HistoryService historyService, HistoryRepository historyRepository, HistoryQueryDslRepository historyDetailRepository, UserRepository userRepository, GoalRepository goalRepository, EntryService entryService) {
        this.historyService = historyService;
        this.historyRepository = historyRepository;
        this.historyDetailRepository = historyDetailRepository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.entryService = entryService;
    }

    @Test
    void 시작하기() {
        Entry entry = getDefaultEntry();
        Long historyId = historyService.start(entry.getId());
        historyService.finish(historyId);

        Assertions.assertThat(historyRepository.findById(historyId).isPresent()).isEqualTo(true);

        Long historyId2 = historyService.start(entry.getId());
        Assertions.assertThat(historyId).isNotEqualTo(historyId2);

        System.out.println("history 목록");
        List<History> historyList = historyService.listByEntry(entry.getId());
        historyList.forEach(history -> System.out.println(history));
    }

    @Test
    void 중복_시작() {
        assertThrows(IllegalStateException.class, () -> historyService.start(-1L));

        Entry entry = getDefaultEntry();
        Long historyId = historyService.start(entry.getId());
        assertThrows(IllegalStateException.class, () -> historyService.start(entry.getId()));
    }



    @Test
    void dslTest() {
        Entry entry = getDefaultEntry();
        Long historyId = historyService.start(entry.getId());
        try {
            Thread.sleep(500L);
        } catch(InterruptedException ie) {
            System.out.println(ie.toString());
        }
        historyService.finish(historyId);

        try {
            Thread.sleep(500L);
        } catch(InterruptedException ie) {
            System.out.println(ie.toString());
        }

        historyId = historyService.start(entry.getId());
        try {
            Thread.sleep(500L);
        } catch(InterruptedException ie) {
            System.out.println(ie.toString());
        }
        //historyService.finish(historyId);

        List<History> byLastHistory = historyDetailRepository.findByLastHistory(entry.getUser().getId());
        byLastHistory.forEach(history -> {
            System.out.println(history);
        });


    }

    Entry getDefaultEntry() {
        User user = new User("qwer@qqwweerr.test", "qwer", "qwer-name");
        Goal goal = new Goal("테스트가 취미1!", "let's test");

        userRepository.save(user);
        goalRepository.save(goal);

        return entryService.entry(user, goal);
    }
}
