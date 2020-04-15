package com.aak.repository;

import com.aak.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ahmed on 21.5.18.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    User findByUserNameOrEmail(String userName, String email);
}
