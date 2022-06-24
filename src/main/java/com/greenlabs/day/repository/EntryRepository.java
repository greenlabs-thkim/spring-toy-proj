package com.greenlabs.day.repository;

import com.greenlabs.day.domain.Entry;
import com.greenlabs.day.domain.Goal;
import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.partial.IGoal;
import com.greenlabs.day.repository.partial.IUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    Optional<Entry> findByUserAndGoal(User user, Goal goal);
    List<IGoal> findAllByUser(User user);
    List<IUser> findAllByGoal(Goal goal);
}
