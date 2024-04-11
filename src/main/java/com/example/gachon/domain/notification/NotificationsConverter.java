package com.example.gachon.domain.notification;

import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;

import java.util.List;

public class NotificationsConverter {

    public static NotificationResponseDto.NotificationInfoDto toNotificationInfoDto(Notifications notifications){
        return NotificationResponseDto.NotificationInfoDto.builder()
                .notificationId(notifications.getId())
                .name(notifications.getName())
                .content(notifications.getContent())
                .type(notifications.getType())
                .isRead(notifications.isRead())
                .userId(notifications.getUser().getId())
                .created_at(notifications.getCreatedAt())
                .updated_at(notifications.getUpdatedAt())
                .build();
    }

    public static NotificationResponseDto.NotificationInfoListDto toNotificationInfoListDto(List<Notifications> notificationsList) {
        List<NotificationResponseDto.NotificationInfoDto> notificationInfoDtoList = notificationsList.stream()
                .map(NotificationsConverter::toNotificationInfoDto)
                .toList();

        return NotificationResponseDto.NotificationInfoListDto.builder()
                .notificationInfoDtoList(notificationInfoDtoList)
                .build();
    }
}
