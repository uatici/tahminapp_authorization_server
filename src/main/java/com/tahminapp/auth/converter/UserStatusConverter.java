package com.tahminapp.auth.converter;

import com.tahminapp.auth.domain.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserStatus userStatus) {
        if(userStatus == null) {
            return null;
        }
        return userStatus.getId();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer id) {
        if(id == null) {
            return null;
        }

        return Stream.of(UserStatus.values())
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
