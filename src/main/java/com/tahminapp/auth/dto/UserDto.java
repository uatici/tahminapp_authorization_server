package com.tahminapp.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tahminapp.auth.domain.Authority;
import com.tahminapp.auth.service.TranslatorService;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    @NotEmpty
    private String userName;


    @NotNull
    @NotEmpty
    @Email
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
