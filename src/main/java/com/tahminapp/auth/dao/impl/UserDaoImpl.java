package com.tahminapp.auth.dao.impl;

import com.tahminapp.auth.dao.UserDao;
import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {

    private UserRepository userRepository;

    public UserDaoImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUserNameOrEmail(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName, email);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
