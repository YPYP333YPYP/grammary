package com.example.gachon.domain.notification;

import com.example.gachon.domain.notice.dto.request.NoticeRequestDto;
import com.example.gachon.domain.notification.dto.request.NotificationRequestDto;
import com.example.gachon.domain.notification.dto.response.NotificationResponseDto;
import com.example.gachon.global.response.ApiResponse;
import com.example.gachon.global.response.code.resultCode.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "admin-notification-controller", description = "관리자 알람 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/notifications")
public class NotificationsAdminController {

    private final NotificationsService notificationsService;

    @GetMapping("/all")
    @Operation(summary = "모든 알람 조회 API ",description = "모든 알람 정보를 가져오기, NotificationInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<NotificationResponseDto.NotificationInfoListDto> getAllNotificationInfoByAdmin(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(notificationsService.getAllNotificationInfo(user.getUsername()));
    }

    @GetMapping("/{notificationId}")
    @Operation(summary = "알람 정보 조회 API ",description = "알람 정보를 가져오기, NotificationInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<NotificationResponseDto.NotificationInfoDto> getNotificationInfoByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                                               @PathVariable Long notificationId){

        return ApiResponse.onSuccess(notificationsService.getNotificationInfoByAdmin(user.getUsername(),notificationId));
    }

    @PatchMapping("/{notificationId}/{userId}")
    @Operation(summary = "유저 알람 송신 API ",description = "유저에게 알람 보내기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SuccessStatus> sendOut(@AuthenticationPrincipal UserDetails user,
                                              @PathVariable Long notificationId, @PathVariable Long userId){
        notificationsService.sendOut(user.getUsername(),notificationId,userId);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PostMapping("")
    @Operation(summary = "알람 생성 API ",description = "알람 생성하기, NotificationDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<SuccessStatus> createNotification(@AuthenticationPrincipal UserDetails user,
                                                   @RequestBody NotificationRequestDto.NotificationDto notificationDto) {
        notificationsService.createNotification(user.getUsername(), notificationDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PatchMapping("/{notificationId}/update")
    @Operation(summary = "알람 정보 수정 API", description = "알람 정보 수정하기, NoticeUpdateDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> updateNotification(@AuthenticationPrincipal UserDetails user,
                                                   @PathVariable Long notificationId,
                                                   @RequestBody NotificationRequestDto.NotificationDto notificationDto) {
        notificationsService.updateNotification(user.getUsername(), notificationId, notificationDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}