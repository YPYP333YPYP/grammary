package com.example.gachon.domain.user;
import com.example.gachon.domain.user.dto.request.UserRequestDto;
import com.example.gachon.domain.user.dto.response.UserResponseDto;
import com.example.gachon.global.response.ApiResponse;
import com.example.gachon.global.response.code.resultCode.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user-controller", description = "사용자 유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/me")
    @Operation(summary = "나의 정보(프로필) 조회 API ",description = " 나의 정보를 가져오기, UserInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<UserResponseDto.UserInfoDto> getUserInfo(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(usersService.getUserInfo(user.getUsername()));
    }

    @GetMapping("/me/history")
    @Operation(summary = "나의 검색 내역 조회 API ",description = " 나의 검색내역을 가져오기, UserHistoryDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<UserResponseDto.UserHistoryListDto> getUserHistory(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(usersService.getUserHistory(user.getUsername()));
    }

    @PatchMapping("/me/info")
    @Operation(summary = "프로필 정보 수정 API", description = "프로필 정보 수정하기, UserNicknameDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> updateUserInfo(@AuthenticationPrincipal UserDetails user, @RequestBody UserRequestDto.UserInfoDto userInfoDto) {
        usersService.updateUserInfo(usersService.getUserId(user), userInfoDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PatchMapping("/me/profileUrl")
    @Operation(summary = "프로필 이미지 수정 API", description = "프로필 이미지 수정하기, UserProfileImageDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "IMAGE403", description = "프로필 이미지가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> updateProfileImage(@AuthenticationPrincipal UserDetails user, @RequestBody UserRequestDto.UserProfileImageDto userProfileImageDto){
        usersService.updateProfileImage(usersService.getUserId(user), userProfileImageDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PatchMapping("/me/nickname")
    @Operation(summary = "프로필 닉네임 수정 API", description = "프로필 닉네임 수정하기, UserNicknameDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> updateUserNickname(@AuthenticationPrincipal UserDetails user, @RequestBody UserRequestDto.UserNicknameDto userNicknameDto) {
        usersService.updateUserNickname(usersService.getUserId(user), userNicknameDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
