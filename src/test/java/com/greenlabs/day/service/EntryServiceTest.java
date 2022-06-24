package com.greenlabs.day.service;


import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.EntryRepository;
import com.greenlabs.day.repository.GoalRepository;
import com.greenlabs.day.repository.UserRepository;
import com.greenlabs.day.repository.partial.IGoal;
import com.greenlabs.day.repository.partial.IUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class EntryServiceTest {
    private final EntryService entryService;
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;


    @Autowired
    public EntryServiceTest(EntryService entryService, EntryRepository entryRepository, UserRepository userRepository, GoalRepository goalRepository)
    {
        this.entryService = entryService;
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
    }

    @Test
    void 가입프로세스() {
        User user = new User("qwer@qqwweerr.test", "qwer", "qwer-name");
        Goal goal = new Goal("테스트가 취미1!", "let's test");

        userRepository.save(user);
        goalRepository.save(goal);

        Entry entry = entryService.entry(user, goal);
        Optional<Entry> byId = entryRepository.findById(entry.getId());

        System.out.println(entry.toString());

        System.out.println("목표리스트");
        List<IGoal> goalList = entryService.listGoalByUser(user);
        goalList.forEach(g -> System.out.println(g.getGoal()));

        System.out.println("유저리스트");
        List<IUser> userList = entryService.listUserByGoal(goal);
        userList.forEach(u -> System.out.println(u.getUser()));


        Assertions.assertThat(entry).isEqualTo(byId.get());

    }

    @Test
    void 이중가입_예외() {
        User user = new User("qwer@qqwweerr.test", "qwer", "qwer-name");
        Goal goal = new Goal("테스트가 취미1!", "let's test");

        userRepository.save(user);
        goalRepository.save(goal);

        entryService.entry(user, goal);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> entryService.entry(user, goal));
        Assertions.assertThat(ex.getMessage()).isEqualTo("이미 가입된 목표그룹 입니다.");


        ex = assertThrows(IllegalStateException.class, () -> entryService.entry(null, goal));
        Assertions.assertThat(ex.getMessage()).isEqualTo("유효하지 않은 사용자입니다.");

        ex = assertThrows(IllegalStateException.class, () -> entryService.entry(user, null));
        Assertions.assertThat(ex.getMessage()).isEqualTo("유효하지 않은 목표입니다.");
    }

}
