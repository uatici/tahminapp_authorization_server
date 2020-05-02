package com.tahminapp.auth.repository;

import com.tahminapp.auth.domain.Authority;
import com.tahminapp.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByAuthority(String authority);
}
