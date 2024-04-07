package com.example.gachon.global.response.code.resultCode;


import com.example.gachon.global.response.code.BaseErrorCode;
import com.example.gachon.global.response.code.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// Enum Naming Format : {주체}_{이유}
// Message format : 동사 명사형으로 마무리
@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL501", "서버 오류"),
    KAKAO_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "GLOBAL502", "토큰관련 서버 에러"),
    INPUT_INVALID_VALUE(HttpStatus.BAD_REQUEST, "GLOBAL401", "잘못된 입력"),

    // OAuth
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "OAUTH401", "기존 토큰이 만료되었습니다. 토큰을 재발급해주세요.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
