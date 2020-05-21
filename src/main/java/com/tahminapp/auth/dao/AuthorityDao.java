package com.tahminapp.auth.dao;

import com.tahminapp.auth.domain.Authority;

public interface AuthorityDao {
    Authority getByAuthority(String authority);
}
