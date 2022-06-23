package com.greenlabs.day;


import com.greenlabs.day.repository.GoalRepository;
import com.greenlabs.day.repository.MemoryGoalRepository;
import com.greenlabs.day.repository.MemoryUserRepository;
import com.greenlabs.day.repository.UserRepository;
import com.greenlabs.day.service.GoalService;
import com.greenlabs.day.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    @Bean
    public GoalService goalService() {
        return new GoalService(goalRepository());
    }

    @Bean
    public GoalRepository goalRepository() {
        return new MemoryGoalRepository();
    }
}
