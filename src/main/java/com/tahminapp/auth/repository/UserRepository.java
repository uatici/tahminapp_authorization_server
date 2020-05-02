package com.tahminapp.auth.repository;

import com.tahminapp.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameOrEmail(String userName, String email);
    User findByEmail(String email);
    User findByUserName (String userName);
}
