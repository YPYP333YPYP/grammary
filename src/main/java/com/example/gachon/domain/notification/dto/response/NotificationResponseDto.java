package com.example.gachon.domain.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationResponseDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationInfoDto{
        private Long notificationId;
        private String name;
        private String content;
        private String type;
        private boolean isRead;
        private Long userId;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationInfoListDto{
       private boolean isSend;
       private Long userId;
       List<NotificationInfoDto> notificationInfoDtoList;
    }
}
