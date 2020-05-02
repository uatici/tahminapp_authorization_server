package com.tahminapp.auth.service.impl;

import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.UserStatus;
import com.tahminapp.auth.domain.VerificationToken;
import com.tahminapp.auth.domain.enums.VerificationTokenStatus;
import com.tahminapp.auth.dto.UserDto;
import com.tahminapp.auth.error.UserAlreadyExistException;
import com.tahminapp.auth.repository.AuthorityRepository;
import com.tahminapp.auth.repository.UserRepository;
import com.tahminapp.auth.repository.VerificationTokenRepository;
import com.tahminapp.auth.service.UserService;
import com.tahminapp.auth.utils.Utils;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Calendar;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private VerificationTokenRepository verificationTokenRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {

        if(emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + userDto.getEmail());
        }

        if(userNameExists(userDto.getUserName())) {
            throw new UserAlreadyExistException("There is an account with that userName : " + userDto.getUserName());
        }
        userDto.setAuthorityList(Arrays.asList(authorityRepository.findByAuthority("ROLE_USER")));
        User user = Utils.convertUserDtoToUserFunction.apply(userDto);
        return userRepository.save(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean userNameExists(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    @Override
    public VerificationTokenStatus validateVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return VerificationTokenStatus.INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return VerificationTokenStatus.EXPIRED;
        }

        user.setStatus(UserStatus.ENABLE);
        // tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return VerificationTokenStatus.VALID;
    }
}
