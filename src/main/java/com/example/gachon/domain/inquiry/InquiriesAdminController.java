package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.inquiry.dto.request.InquiryRequestDto;
import com.example.gachon.domain.inquiry.dto.response.InquiryResponseDto;
import com.example.gachon.domain.notification.dto.request.NotificationRequestDto;
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


@Tag(name = "admin-inquiry-controller", description = "관리자 문의 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/inquiries")
public class InquiriesAdminController {

    private final InquiriesService inquiriesService;

    @GetMapping("/{inquiryId}")
    @Operation(summary = "문의 정보 조회 API ", description = "문의 정보 가져오기, InquiryInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })

    public ApiResponse<InquiryResponseDto.InquiryInfoDto> getInquiryInfoByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                         @PathVariable Long inquiryId) {

        return ApiResponse.onSuccess(inquiriesService.getInquiryInfoByAdmin(user.getUsername(), inquiryId));
    }

    @GetMapping("/all")
    @Operation(summary = "모든 문의 정보 조회 API ", description = "모든 문의 정보 가져오기, InquiryInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })

    public ApiResponse<List<InquiryResponseDto.InquiryInfoDto>> getInquiryListByAdmin(@AuthenticationPrincipal UserDetails user) {

        return ApiResponse.onSuccess(inquiriesService.getInquiryListByAdmin(user.getUsername()));
    }

    @PatchMapping("/{inquiryId}/update")
    @Operation(summary = "문의 답변 API", description = "문의 답변 하기, InquiryAnswerDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> answerInquiry(@AuthenticationPrincipal UserDetails user,
                                                    @PathVariable Long inquiryId,
                                                    @RequestBody InquiryRequestDto.InquiryAnswerDto inquiryAnswerDto) {
        inquiriesService.answerInquiry(user.getUsername(), inquiryId, inquiryAnswerDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @DeleteMapping("/{inquiryId}/delete")
    @Operation(summary = "문의 삭제 요청 API ",description = "문의 삭제 하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SuccessStatus> deleteInquiryByAdmin(@AuthenticationPrincipal UserDetails user,
                                                        @PathVariable Long inquiryId){
        inquiriesService.deleteInquiryByAdmin(user.getUsername(), inquiryId);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}