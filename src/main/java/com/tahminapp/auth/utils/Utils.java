package com.tahminapp.auth.utils;

import com.tahminapp.auth.domain.Authority;
import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.UserStatus;
import com.tahminapp.auth.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;
import java.util.function.Function;

/**
 * Created by ahmed on 29.5.18.
 */
public interface Utils {


    static String passwordEncoder(String password){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        return encodedPassword;
    }

    Function<UserDto, User> convertUserDtoToUserFunction = (userDto ->
            new User(userDto.getUserName(),
                     userDto.getEmail(),
                     Utils.passwordEncoder(userDto.getPassword()),
                    userDto.getName(),
                    userDto.getSurName(),
                    UserStatus.VERIFY,
                    userDto.getAuthorityList())

    );
}
