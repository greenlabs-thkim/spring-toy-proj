package com.greenlabs.day;


import com.greenlabs.day.repository.*;
import com.greenlabs.day.service.GoalService;
import com.greenlabs.day.service.EntryService;
import com.greenlabs.day.service.HistoryService;
import com.greenlabs.day.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final EntryRepository entryRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, GoalRepository goalRepository, EntryRepository entryRepository, HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.entryRepository = entryRepository;
        this.historyRepository = historyRepository;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    public GoalService goalService() {
        return new GoalService(goalRepository);
    }

    @Bean
    public EntryService entryService() {
        return new EntryService(entryRepository, userRepository, goalRepository);
    }

    @Bean
    public HistoryService historyService() {
        return new HistoryService(historyRepository, entryRepository);
    }
}
