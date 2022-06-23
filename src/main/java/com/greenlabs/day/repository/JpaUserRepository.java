package com.greenlabs.day.repository;

import com.greenlabs.day.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends CrudRepository<User, Long>, UserRepository {
    @Override
    User save(User user) ;

    @Override
    Optional<User> findById(long id);

    @Override
    Optional<User> findByEmail(String email);

    @Override
    List<User> findAll();
}
