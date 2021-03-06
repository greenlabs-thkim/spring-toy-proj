package com.greenlabs.day.service;

import com.greenlabs.day.domain.User;
import com.greenlabs.day.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceTest {
    UserRepository userRepository;
    UserService userService;

    @Autowired
    void UserServiceTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    void login() {
        User user = new User();
        user.setEmail("email1");
        user.setDisplayName("user1");
        user.setName("user1");
        user.setPassword("password1");
        userService.regist(user);

        User user2 = new User();
        user2.setEmail("email2");
        user2.setDisplayName("user2");
        user2.setName("user2");
        user2.setPassword("password2");
        userService.regist(user2);

        User loginUser = userService.login(user);
        Assertions.assertThat(user).isEqualTo(loginUser);
    }

    @Test
    void 로그인_예외케이스() {
        User user = new User();
        user.setEmail("email1");
        user.setDisplayName("user1");
        user.setName("user1");
        user.setPassword("password1");
        userService.regist(user);

        User user2 = new User();
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> userService.login(user2));
        Assertions.assertThat(ex.getMessage()).isEqualTo("email 은 필수 입력값 입니다.");

        user2.setEmail("email2");
        ex = assertThrows(IllegalStateException.class, () -> userService.login(user2));
        Assertions.assertThat(ex.getMessage()).isEqualTo("password 는 필수 입력값 입니다.");

        user2.setPassword("password2");
        ex = assertThrows(IllegalStateException.class, () -> userService.login(user2));
        Assertions.assertThat(ex.getMessage()).isEqualTo("존재하지 않는 사용자입니다.");

        user2.setEmail("email1");
        user2.setPassword("password2");
        ex = assertThrows(IllegalStateException.class, () -> userService.login(user2));
        Assertions.assertThat(ex.getMessage()).isEqualTo("유효하지 않은 비밀번호입니다.");

        user2.setPassword("password1");
        User loginUser = userService.login(user);
        Assertions.assertThat(user).isEqualTo(loginUser);
    }
}
