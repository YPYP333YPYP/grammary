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

}