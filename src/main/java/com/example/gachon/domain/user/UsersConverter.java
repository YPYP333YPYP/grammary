package com.example.gachon.domain.user;

import com.example.gachon.domain.token.dto.request.SignUpRequestDto;

public class UsersConverter {

    private static final String DEFAULT_PROFILE_URL = "";

    public static Users toSignUpDto(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phoneNum(signUpRequestDto.getPhoneNum())
                .profileUrl(DEFAULT_PROFILE_URL)
                .status(Status.ENABLED)
                .build();
    }
}
