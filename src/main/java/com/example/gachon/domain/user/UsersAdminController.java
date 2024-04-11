package com.example.gachon.domain.user;

import com.example.gachon.domain.user.dto.response.UserResponseDto;
import com.example.gachon.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "admin-user-controller", description = "관리자 유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/users")
public class UsersAdminController {

    private final UsersService usersService;

    @GetMapping("/{userId}")
    @Operation(summary = "유저 정보 조회 API ", description = " 유저의 정보를 가져오기, UserInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })

    public ApiResponse<UserResponseDto.UserInfoDto> getUserInfoByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                       @PathVariable Long userId) {

        return ApiResponse.onSuccess(usersService.getUserInfoByAdmin(user.getUsername(), userId));
    }

    @GetMapping("/{userId}/notifications")
    @Operation(summary = "유저 수신 알람 조회 API ", description = " 유저가 받은 알람을 조회하기, UserNotificationListDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })

    public ApiResponse<UserResponseDto.UserNotificationListDto> getUserNotificationByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                       @PathVariable Long userId) {

        return ApiResponse.onSuccess(usersService.getUserNotificationByAdmin(user.getUsername(), userId));
    }

    @GetMapping("/histories/{userId}")
    @Operation(summary = "유저 검색 내역 조회 API ", description = " 유저가 검색한 문장 내역 조회하기 ")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })

    public ApiResponse<List<Long>> getUserHistoryByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                                           @PathVariable Long userId) {

        return ApiResponse.onSuccess(usersService.getUserHistoryByAdmin(user.getUsername(), userId));
    }
}