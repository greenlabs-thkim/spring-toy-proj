package com.greenlabs.day.service;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.repository.GoalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class GoalServiceTest {

    GoalRepository repository;
    GoalService service;

    @Autowired
    void GoalServiceTest(GoalRepository repository, GoalService service) {
        this.repository =repository;
        this.service = service;
    }

    @Test
    void 등록() {
        Goal g = new Goal();
        g.setName("운동212121111");
        g.setDescription("함께 운동합시다.");
        long retId = service.regist(g);

        Assertions.assertThat(g).isEqualTo(repository.findById(retId).get());
    }


    @Test
    void 조회() {
        Goal g = new Goal();
        g.setName("운동232223233222");
        g.setDescription("함께 운동합시다.");
        long retId = service.regist(g);

        g = new Goal();
        g.setName("독서323232323232");
        g.setDescription("함께 독서합시다.");
        service.regist(g);

        Goal findG = new Goal();
        findG.setName("운동232223233222");

        List<Goal> goalList = service.search(findG);
        Assertions.assertThat(goalList.size()).isEqualTo(1);
        Assertions.assertThat(retId).isEqualTo(goalList.get(0).getId());
    }
}
