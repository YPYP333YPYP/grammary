package com.example.gachon.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
public class UserResponseDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoDto{
        private Long userId;
        private String name;
        private String nickname;
        private String profileUrl;
        private String phoneNum;
        private String social;

    }
}
