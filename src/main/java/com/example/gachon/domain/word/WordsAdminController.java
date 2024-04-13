package com.example.gachon.domain.word;

import com.example.gachon.domain.notification.dto.request.NotificationRequestDto;
import com.example.gachon.domain.sentence.dto.request.SentenceRequestDto;
import com.example.gachon.domain.word.dto.request.WordRequestDto;
import com.example.gachon.domain.word.dto.response.WordResponseDto;
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

    @GetMapping("/all")
    @Operation(summary = "모든 단어 리스트 조회 API ",description = "모든 단어 리스트 가져오기, WordInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })

    public ApiResponse<List<WordResponseDto.WordInfoDto>> getWordListByAdmin(@AuthenticationPrincipal UserDetails user){

        return ApiResponse.onSuccess(wordsService.getWordListByAdmin(user.getUsername()));
    }

    @PostMapping("")
    @Operation(summary = "단어 생성 API ",description = "단어 여러 개 생성하기, WordListDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<SuccessStatus> createWord(@AuthenticationPrincipal UserDetails user,
                                                   @RequestBody List<WordRequestDto.WordDto> wordListDto) {
        wordsService.createWord(user.getUsername(), wordListDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PatchMapping("/{wordId}/update")
    @Operation(summary = "단어 수정 API", description = "단어 수정 하기, WordDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> updateWord(@AuthenticationPrincipal UserDetails user,
                                                              @PathVariable Long wordId,
                                                              @RequestBody WordRequestDto.WordDto wordDto) {
        wordsService.updateWord(user.getUsername(), wordId, wordDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}