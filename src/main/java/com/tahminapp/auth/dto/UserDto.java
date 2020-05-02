package com.tahminapp.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tahminapp.auth.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surName;

    @JsonIgnore
    List<Authority> authorityList;


}
