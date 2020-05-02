package com.tahminapp.auth.service;

import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.enums.VerificationTokenStatus;
import com.tahminapp.auth.dto.UserDto;

public interface UserService {

    User registerNewUserAccount(UserDto userDto);
    boolean emailExists(String email);
    boolean userNameExists(String userName);
    void createVerificationTokenForUser(User user, String token);
    VerificationTokenStatus validateVerificationToken(String token);

}
