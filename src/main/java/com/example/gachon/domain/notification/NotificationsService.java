package com.example.gachon.domain.notification;

import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UsersRepository usersRepository;

    List<NotificationResponseDto.NotificationInfoDto> getNotificationInfo(String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow( () -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        List<Notifications> notifications = notificationsRepository.getAllByUser(user);

        return notifications.stream()
                .map(NotificationsConverter::toNotificationInfoDto)
                .collect(Collectors.toList());
    }
}
