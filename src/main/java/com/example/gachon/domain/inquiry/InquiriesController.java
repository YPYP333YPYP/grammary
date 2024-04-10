package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.inquiry.dto.request.InquiryRequestDto;
import com.example.gachon.domain.inquiry.dto.response.InquiryResponseDto;
import com.example.gachon.domain.word.dto.response.WordResponseDto;
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

@Tag(name = "inquiry-controller", description = "사용자 문의 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/inquiries")
public class InquiriesController {

    private final InquiriesService inquiriesService;

    @GetMapping("/{inquiryId}")
    @Operation(summary = "문의 정보 조회 API ",description = "문의 정보 가져오기, InquiryInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<InquiryResponseDto.InquiryInfoDto> getInquiryInfo(@AuthenticationPrincipal UserDetails user,
                                                                         @PathVariable Long inquiryId){

        return ApiResponse.onSuccess(inquiriesService.getInquiryInfo(inquiryId));
    }

    @GetMapping("/me")
    @Operation(summary = "나의 모든 문의 리스트 조회 API ",description = "모든 문의 리스트 가져오기,  InquiryInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<List<InquiryResponseDto.InquiryInfoDto>> getInquiryList(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(inquiriesService.getInquiryList(user.getUsername()));
    }

    @PostMapping("")
    @Operation(summary = "문의 요청 API ",description = "문의 요청 하기,  InquiryDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SuccessStatus> createInquiry(@AuthenticationPrincipal UserDetails user,
                                                    @RequestBody InquiryRequestDto.InquiryDto inquiryDto){

        inquiriesService.createInquiry(user.getUsername(), inquiryDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @DeleteMapping ("/inquiries/{inquiryId}/delete")
    @Operation(summary = "문의 삭제 요청 API ",description = "문의 삭제 하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SuccessStatus> deleteInquiry(@AuthenticationPrincipal UserDetails user,
                                                    @PathVariable Long inquiryId){
        inquiriesService.deleteInquiry(user.getUsername(), inquiryId);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
