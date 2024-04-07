package com.example.gachon.global.response.code.resultCode;


import com.example.gachon.global.response.code.ReasonDto;
import com.example.gachon.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// Enum Naming Format : {행위}_{목적어}_{성공여부}
// Message Format : 동사 명사형으로 마무리
@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // OAuth
    OAUTH_USER_CREATE_SUCCESS(HttpStatus.OK,"0AUTH001", "소셜 로그인 사용자 생성 성공");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
