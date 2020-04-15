package com.tahminapp.auth.configuration;

import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.UserStatus;
import com.tahminapp.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by ahmed on 21.5.18.
 */
public class JdbcUserDetails implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(userNameOrEmail,userNameOrEmail);
        if(user==null){
            throw new UsernameNotFoundException("User"+userNameOrEmail+"can not be found");
        }
        org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),user.getStatus() == UserStatus.ENABLE,true,true,true,user.getAuthorities());
        return  authUser;
    }
}
