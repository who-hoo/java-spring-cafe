package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.*;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("UserService 클래스")
class UserServiceTest {

    UserService userService;
    UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);

        User alreadyPresentUser = new User();
        alreadyPresentUser.setEmail("already@present.user");
        alreadyPresentUser.setNickname("already present user1");
        alreadyPresentUser.setPassword("password1");
        userRepository.save(alreadyPresentUser);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    class signUp_메소드는 {

        private final User givenNonDuplicatedUser = new User();
        private final User givenDuplicatedUser = new User();

        @BeforeEach
        void beforeEach() {
            givenNonDuplicatedUser.setEmail("new@test.user");
            givenNonDuplicatedUser.setNickname("test user1");
            givenNonDuplicatedUser.setPassword("password1");

            givenDuplicatedUser.setEmail("already@present.user");
            givenDuplicatedUser.setNickname("test user1");
            givenDuplicatedUser.setPassword("password1");
        }

        @Nested
        @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
        class 중복된_이메일을_가진_회원이_주어지면 {

            @Test
            @DisplayName("\"이미 존재하는 회원입니다.\"라는 IllegalStateException을 던진다")
            void 이미_존재하는_회원입니다_라는_IllegalStateException을_던진다() {
                assertThatThrownBy(() -> userService.signUp(givenDuplicatedUser))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 존재하는 회원입니다.");
            }
        }

        @Nested
        @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
        class 중복되지_않은_이메일을_가진_회원이_주어지면 {

            @Test
            @DisplayName("주어진 회원을 저장하고 저장된 회원의 id를 리턴한다")
            void 주어진_회원을_저장하고_저장된_회원의_id를_리턴한다() {
                assertThat(userService.signUp(givenNonDuplicatedUser)).isEqualTo(2);
            }
        }
    }
}
