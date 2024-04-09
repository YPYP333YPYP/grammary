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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "word-controller", description = "사용자 단어 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/words")
public class WordsController {

    private final WordsService wordsService;

    @GetMapping("/all")
    @Operation(summary = "모든 문장 리스트 조회 API ",description = "모든 문장 리스트 가져오기, WordInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<List<WordResponseDto.WordInfoDto>> getWordList(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(wordsService.getWordList());
    }
}
