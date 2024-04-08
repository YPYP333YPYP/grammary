package com.example.gachon.domain.notification;

import com.example.gachon.domain.notice.dto.response.NoticeResponseDto;
import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import com.example.gachon.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notifications")
public class NotificationsController {


    private final NotificationsService notificationsService;
    @GetMapping("/me")
    @Operation(summary = "나의 알람 정보 조회 API ",description = "나의 알람 정보를 가져오기, NotificationInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<List<NotificationResponseDto.NotificationInfoDto>> getNotificationInfo(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(notificationsService.getNotificationInfo(user.getUsername()));
    }
}
