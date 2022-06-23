package com.greenlabs.day.service;

import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.repository.MemoryGoalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GoalServiceTest {

    MemoryGoalRepository repository = new MemoryGoalRepository();
    GoalService service = new GoalService(repository);

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }


    @Test
    void 등록() {
        Goal g = new Goal();
        g.setName("운동");
        g.setDescription("함께 운동합시다.");
        long retId = service.regist(g);

        Assertions.assertThat(g).isEqualTo(repository.findById(retId).get());
    }


    @Test
    void 조회() {
        Goal g = new Goal();
        g.setName("운동");
        g.setDescription("함께 운동합시다.");
        long retId = service.regist(g);

        g = new Goal();
        g.setName("독서");
        g.setDescription("함께 독서합시다.");
        service.regist(g);

        Goal findG = new Goal();
        findG.setName("운동");

        List<Goal> goalList = service.search(findG);
        Assertions.assertThat(goalList.size()).isEqualTo(1);
        Assertions.assertThat(retId).isEqualTo(goalList.get(0).getId());
    }
}
