package com.example.gachon.domain.notification;

import com.example.gachon.domain.notice.Notices;
import com.example.gachon.domain.notice.NoticesConverter;
import com.example.gachon.domain.notice.dto.request.NoticeRequestDto;
import com.example.gachon.domain.notification.dto.request.NotificationRequestDto;
import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.GeneralHandler;
import com.example.gachon.global.response.exception.handler.NotificationsHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UsersRepository usersRepository;

    public List<NotificationResponseDto.NotificationInfoDto> getNotificationInfo(String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow( () -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        List<Notifications> notifications = notificationsRepository.getAllByUser(user);

        return notifications.stream()
                .map(NotificationsConverter::toNotificationInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateIsRead(Long notificationId) {
        Notifications notification = notificationsRepository.getById(notificationId);
        boolean isRead = notification.isRead();

        if (isRead) {
            throw new NotificationsHandler(ErrorStatus.NOTIFICATION_ALREADY_READ);
        } else {
            notification.setRead(true);
            notificationsRepository.save(notification);
        }
    }

    public NotificationResponseDto.NotificationInfoListDto getAllNotificationInfo(String email) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            List<Notifications> notifications = notificationsRepository.findAll();

            return NotificationsConverter.toNotificationInfoListDto(notifications);

        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }
    }

    public NotificationResponseDto.NotificationInfoDto getNotificationInfoByAdmin(String email, Long notificationId) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Notifications notification = notificationsRepository.findById(notificationId).orElseThrow(()->new NotificationsHandler(ErrorStatus.NOTIFICATION_NOT_FOUND));

            return NotificationsConverter.toNotificationInfoDto(notification);

        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }


    }

    @Transactional
    public void sendOut(String email, Long notificationId, Long userId) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Notifications notification = notificationsRepository.findById(notificationId).orElseThrow(()->new NotificationsHandler(ErrorStatus.NOTIFICATION_NOT_FOUND));
            Users user = usersRepository.findById(userId).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

            notification.setUser(user);
            notificationsRepository.save(notification);

        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    public void createNotification(String email, NotificationRequestDto.NotificationDto notificationDto) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Notifications notification = Notifications.builder()
                    .name(notificationDto.getName())
                    .content(notificationDto.getContent())
                    .type(notificationDto.getType())
                    .isRead(notificationDto.isRead())
                    .user(reqUser)
                    .build();

            notificationsRepository.save(notification);
        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    public void updateNotification(String email, Long notificationId, NotificationRequestDto.NotificationDto notificationDto) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Notifications notification = notificationsRepository.findById(notificationId).orElseThrow(()->new NotificationsHandler(ErrorStatus.NOTIFICATION_NOT_FOUND));

            notification.setName(notificationDto.getName());
            notification.setContent(notification.getContent());
            notification.setType(notification.getType());

            notificationsRepository.save(notification);
        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }
    }
}
