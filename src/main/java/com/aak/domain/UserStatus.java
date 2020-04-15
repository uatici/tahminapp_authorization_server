package com.aak.domain;

public enum UserStatus {
    ENABLE(1), DISABLE(2), VERIFY(3);

    private Integer id;

    private UserStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
