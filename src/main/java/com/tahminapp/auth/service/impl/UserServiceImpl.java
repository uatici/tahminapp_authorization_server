package com.tahminapp.auth.service.impl;

import com.tahminapp.auth.dao.AuthorityDao;
import com.tahminapp.auth.dao.UserDao;
import com.tahminapp.auth.dao.VerificationTokenDao;
import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.UserStatus;
import com.tahminapp.auth.domain.VerificationToken;
import com.tahminapp.auth.domain.enums.VerificationTokenStatus;
import com.tahminapp.auth.dto.UserDto;
import com.tahminapp.auth.customexceptions.UserAlreadyExistException;
import com.tahminapp.auth.service.TranslatorService;
import com.tahminapp.auth.service.UserService;
import com.tahminapp.auth.utils.Utils;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Calendar;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private AuthorityDao authorityDao;
    private VerificationTokenDao verificationTokenDao;


    public UserServiceImpl(UserDao userDao, AuthorityDao authorityDao, VerificationTokenDao verificationTokenDao) {
        this.userDao = userDao;
        this.authorityDao = authorityDao;
        this.verificationTokenDao = verificationTokenDao;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {

        if(emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException(TranslatorService.toLocale("error.alreadyexist"));
        }

        if(userNameExists(userDto.getUserName())) {
            throw new UserAlreadyExistException(TranslatorService.toLocale("error.alreadyexist"));
        }
        userDto.setAuthorityList(Arrays.asList(authorityDao.getByAuthority("ROLE_USER")));
        User user = Utils.convertUserDtoToUserFunction.apply(userDto);
        return userDao.save(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenDao.save(myToken);
    }

    @Override
    public boolean emailExists(String email) {
        return userDao.getByEmail(email) != null;
    }

    @Override
    public boolean userNameExists(String userName) {
        return userDao.getByUserName(userName) != null;
    }

    @Override
    public VerificationTokenStatus validateVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenDao.getByToken(token);
        if (verificationToken == null) {
            return VerificationTokenStatus.INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            verificationTokenDao.remove(verificationToken);
            return VerificationTokenStatus.EXPIRED;
        }

        user.setStatus(UserStatus.ENABLE);
        // tokenRepository.delete(verificationToken);
        userDao.save(user);
        return VerificationTokenStatus.VALID;
    }
}
