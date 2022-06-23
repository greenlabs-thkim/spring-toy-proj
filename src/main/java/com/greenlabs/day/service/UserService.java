package com.greenlabs.day.service;

import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        userRepository = repository;
    }

    public long regist(User user) {
        validateUserInfo(user, false);

        userRepository.save(user);
        return user.getId();
    }

    public User login(User user) {
        validateUserInfo(user, true);

        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            if (findUser.get().getPassword().equals(user.getPassword())) {
                return findUser.get();
            } else {
                throw new IllegalStateException("유효하지 않은 비밀번호입니다.");
            }
        } else {
            throw new IllegalStateException("존재하지 않는 사용자입니다.");
        }
    }

    private void validateUserInfo(User user, boolean checkOnlyLogin) {
        if (user.getEmail() == null) {
            throw new IllegalStateException("email 은 필수 입력값 입니다.");
        }

        if (user.getPassword() == null) {
            throw new IllegalStateException("password 는 필수 입력값 입니다.");
        }

        if (checkOnlyLogin) {
            return ;
        }

        if (user.getDisplay_name() == null) {
            throw new IllegalStateException("display_name 은 필수 입력값 입니다.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }
    }
}
