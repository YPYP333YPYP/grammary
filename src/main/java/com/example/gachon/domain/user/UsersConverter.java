package com.example.gachon.domain.user;

import com.example.gachon.domain.token.dto.request.SignUpRequestDto;

public class UsersConverter {

    private static final String DEFAULT_PROFILE_URL = "";

    public static Users toUserSignUpDto(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phoneNum(signUpRequestDto.getPhoneNum())
                .social(signUpRequestDto.getSocial())
                .role("USER")
                .profileUrl(DEFAULT_PROFILE_URL)
                .status(Status.ENABLED)
                .build();
    }

    public static Users toAdminSignUpDto(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phoneNum(signUpRequestDto.getPhoneNum())
                .social(signUpRequestDto.getSocial())
                .role("ADMIN")
                .profileUrl(DEFAULT_PROFILE_URL)
                .status(Status.ENABLED)
                .build();
    }
}
