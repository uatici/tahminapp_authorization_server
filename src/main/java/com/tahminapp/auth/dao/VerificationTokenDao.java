package com.tahminapp.auth.dao;

import com.tahminapp.auth.domain.User;
import com.tahminapp.auth.domain.VerificationToken;
import java.util.Date;
import java.util.stream.Stream;

public interface VerificationTokenDao {

    VerificationToken getByToken(String token);
    VerificationToken getByUser(User user);
    Stream<VerificationToken> getAllByExpiryDateLessThan(Date now);
    void removeByExpiryDateLessThan(Date now);
    void removeAllExpiredSince(Date now);
    void remove(VerificationToken verificationToken);
    VerificationToken save (VerificationToken verificationToken);

}
