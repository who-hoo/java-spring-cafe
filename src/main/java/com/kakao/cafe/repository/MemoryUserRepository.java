package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static List<User> store = new ArrayList<>();
    private static Long sequence = 0L;

    @Override
    public User save(User user) {
        User userRecord = User.createUserRecord(++sequence, user);
        store.add(userRecord);
        return userRecord;
    }

    @Override
    public Optional<User> findById(Long id) {
        return store.stream()
            .filter(user -> user.hasSameId(id))
            .findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.stream()
            .filter(user -> user.hasSameEmail(email))
            .findAny();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(store);
    }
}
