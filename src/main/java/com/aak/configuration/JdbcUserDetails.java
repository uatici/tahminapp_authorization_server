package com.aak.configuration;

import com.aak.domain.User;
import com.aak.domain.UserStatus;
import com.aak.repository.UserRepository;
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
