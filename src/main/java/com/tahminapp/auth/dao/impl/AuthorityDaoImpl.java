package com.tahminapp.auth.dao.impl;

import com.tahminapp.auth.dao.AuthorityDao;
import com.tahminapp.auth.domain.Authority;
import com.tahminapp.auth.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityDaoImpl implements AuthorityDao {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityDaoImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }
}
