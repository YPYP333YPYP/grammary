package com.example.gachon.domain.user.dto.response;

import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserHistoryDto{
        private Long sentenceId;
        private String name;
        private String content;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserHistoryListDto{
        private Long userId;
        private List<UserHistoryDto> userHistoryDtoList;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationListDto{
        private Long userId;
        private List<NotificationResponseDto.NotificationInfoDto> notificationInfoList;

    }

}
