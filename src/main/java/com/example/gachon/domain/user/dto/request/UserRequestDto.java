package com.example.gachon.domain.user.dto.request;

import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class UserNicknameDto {
        private String nickname;
    }

    @Getter
    public static class UserProfileImageDto {
        private Long imageId;
    }

    @Getter
    public static class UserInfoDto {
        private String phoneNum;
        private String social;
    }

    @Getter
    public static class UserUpdateDto {
        private String email;
        private String social;
        private String phoneNum;
        private String name;
        private String nickname;
        private String password;
        private String status;

    }
}
