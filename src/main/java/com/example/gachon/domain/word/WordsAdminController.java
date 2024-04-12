package com.example.gachon.domain.word;

import com.example.gachon.domain.word.dto.response.WordResponseDto;
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


@Tag(name = "admin-word-controller", description = "관리자 단어 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/words")
public class  WordsAdminController {

    private final WordsService wordsService;
    @GetMapping("/{wordId}")
    @Operation(summary = "단어 정보 조회 API ",description = "단어 정보 가져오기, WordInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<WordResponseDto.WordInfoDto> getWordInfoByAdmin(@AuthenticationPrincipal UserDetails user,
                                                                             @PathVariable Long wordId){

        return ApiResponse.onSuccess(wordsService.getWordInfoByAdmin(user.getUsername(), wordId));
    }
}