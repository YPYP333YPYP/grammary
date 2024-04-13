package com.example.gachon.domain.notification.dto.request;

import lombok.Builder;
import lombok.Getter;

public class NotificationRequestDto {

    @Getter
    @Builder
    public static class NotificationDto {
        private String name;
        private String content;
        private String type;
        private boolean isRead;
    }
}
