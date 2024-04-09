package com.example.gachon.domain.sentence;

import com.example.gachon.domain.sentence.dto.response.SentenceResponseDto;
import com.example.gachon.domain.user.dto.response.UserResponseDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sentences")
public class SentencesController {

    private final SentencesService sentencesService;

    @GetMapping("/{sentenceId}/info")
    @Operation(summary = "문장 정보 조회 API ",description = " 문장 정보를 가져오기, SentenceInfoDto, SentenceComponentInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<SentenceResponseDto.SentenceInfoDto> getSentenceInfo(@PathVariable Long sentenceId){

        return ApiResponse.onSuccess(sentencesService.getSentenceInfo(sentenceId));
    }
}
