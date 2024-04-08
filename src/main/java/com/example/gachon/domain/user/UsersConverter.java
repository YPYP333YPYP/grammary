package com.example.gachon.domain.user;

import com.example.gachon.domain.token.dto.request.SignUpRequestDto;
import com.example.gachon.domain.user.dto.response.UserResponseDto;

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

    public static UserResponseDto.UserInfoDto toUserInfoDto(Users user){
        return UserResponseDto.UserInfoDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .phoneNum(user.getPhoneNum())
                .social(user.getSocial())
                .build();
    }
}
