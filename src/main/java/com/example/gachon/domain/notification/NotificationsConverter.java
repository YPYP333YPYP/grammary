package com.example.gachon.domain.notification;

import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;

public class NotificationsConverter {

    public static NotificationResponseDto.NotificationInfoDto toNotificationInfoDto(Notifications notifications){
        return NotificationResponseDto.NotificationInfoDto.builder()
                .notificationId(notifications.getId())
                .name(notifications.getName())
                .content(notifications.getContent())
                .type(notifications.getType())
                .isRead(notifications.isRead())
                .created_at(notifications.getCreatedAt())
                .updated_at(notifications.getUpdatedAt())
                .build();
    }
}
