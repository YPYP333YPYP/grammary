package com.example.gachon.domain.notification;

import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import com.example.gachon.global.response.ApiResponse;
import com.example.gachon.global.response.code.resultCode.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "notification-controller", description = "사용자 알람 관련 API")

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

    @PatchMapping("/{notificationId}/read")
    @Operation(summary = "알람 읽음 API ",description = "알람을 읽음 상태로 수정하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SuccessStatus> updateIsRead(@AuthenticationPrincipal UserDetails user,
                                                                                       @PathVariable Long notificationId){
        notificationsService.updateIsRead(notificationId);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
