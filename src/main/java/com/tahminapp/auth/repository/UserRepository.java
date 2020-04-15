package com.tahminapp.auth.repository;

import com.tahminapp.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ahmed on 21.5.18.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
    User findByUserNameOrEmail(String userName, String email);
}
