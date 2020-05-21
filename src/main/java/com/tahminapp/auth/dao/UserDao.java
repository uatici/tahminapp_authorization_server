package com.tahminapp.auth.dao;

import com.tahminapp.auth.domain.User;

public interface UserDao {
    User getByUserNameOrEmail(String userName, String email);
    User getByEmail(String email);
    User getByUserName (String userName);
    User save (User user);
}
