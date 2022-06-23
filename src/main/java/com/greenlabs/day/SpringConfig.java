package com.greenlabs.day;


import com.greenlabs.day.repository.*;
import com.greenlabs.day.service.GoalService;
import com.greenlabs.day.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class SpringConfig {
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, GoalRepository goalRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
    }

    @Bean
    public UserService userService() {
        //return new UserService(userRepository());
        return new UserService(userRepository);
    }

    // @Bean
//    public UserRepository userRepository() {
//        return new JpaUserRepository();
//    }

    @Bean
    public GoalService goalService() {
        return new GoalService(goalRepository);
    }

//    @Bean
//    public GoalRepository goalRepository() {
//        return new MemoryGoalRepository();
//    }
}
