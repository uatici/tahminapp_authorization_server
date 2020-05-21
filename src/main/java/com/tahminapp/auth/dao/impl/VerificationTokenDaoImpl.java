package com.tahminapp.auth.dao.impl;

import com.tahminapp.auth.dao.VerificationTokenDao;
import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.VerificationToken;
import com.tahminapp.auth.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
public class VerificationTokenDaoImpl implements VerificationTokenDao {

    private VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenDaoImpl (VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken getByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken getByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Override
    public Stream<VerificationToken> getAllByExpiryDateLessThan(Date now) {
        return verificationTokenRepository.findAllByExpiryDateLessThan(now);
    }

    @Override
    public void removeByExpiryDateLessThan(Date now) {
        verificationTokenRepository.deleteByExpiryDateLessThan(now);
    }

    @Override
    public void removeAllExpiredSince(Date now) {
        verificationTokenRepository.deleteAllExpiredSince(now);
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void remove(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
