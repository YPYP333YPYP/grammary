package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.inquiry.dto.response.InquiryResponseDto;
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
}