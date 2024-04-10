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

    // User
    USER_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "USER401", "중복된 이메일입니다."),
    USER_EXISTS_NAME(HttpStatus.BAD_REQUEST, "USER402", "중복된 이름입니다."),
    USER_FAILED_TO_PASSWORD(HttpStatus.BAD_REQUEST, "USER403", "비밀번호가 잘못되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "유저를 찾을 수 없습니다."),

    NICKNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "USER407", "존재하지 않는 유저 닉네임입니다."),
    NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "USER408", "존재하지 않는 유저 이름입니다."),
    USER_CREATE_FAIL(HttpStatus.BAD_REQUEST,"USER409", "유저 생성에 실패하였습니다."),
    USER_DELETE_FAIL(HttpStatus.BAD_REQUEST,"USER410", "유저 삭제에 실패하였습니다."),
    USER_EXISTS_NICKNAME(HttpStatus.BAD_REQUEST, "USER411", "중복된 닉네임입니다."),
    USER_PHONE_NUM_NOT_FOUND(HttpStatus.NOT_FOUND, "USER412", "존재하지 않는 전화번호입니다."),
    USER_EXISTS_NO_INPUT_EMAIL(HttpStatus.BAD_REQUEST, "USER413", "이메일을 입력하지 않았습니다."),
    USER_ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "USER414", "존재하지 않는 계정입니다."),
    USER_EMAIL_PASSWORD_NOT_EMPTY(HttpStatus.BAD_REQUEST, "USER415", "이메일 또는 비밀번호를 입력하지 않았습니다."),

    // Notice
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTICE404", "공지 사항을 찾을 수 없습니다."),

    // Sentence
    SENTENCE_NOT_FOUND(HttpStatus.NOT_FOUND, "SENTENCE404", "문장을 찾을 수 없습니다."),

    // SentenceInfo
    SENTENCE_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "SENTENCEINFO404", "문장 정보를 찾을 수 없습니다"),

    // Note
    NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTE404", "노트를 찾을 수 없습니다"),

    // Inquiry
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRY404", "문의를 찾을 수 없습니다"),

    // OAuth
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "OAUTH401", "기존 토큰이 만료되었습니다. 토큰을 재발급해주세요."),

    // Image
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IMAGE401", "파일이 존재하지 않습니다."),
    IMAGE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "IMAGE402", "이미지 업로드에 실패하였습니다."),
    IMAGE_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "IMAGE403","이미지가 제공되지 않았습니다" ),
    
    // Memo
    MEMO_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMO404", "메모를 찾을 수 없습니다"),

    // Notification
    NOTIFICATION_ALREADY_READ(HttpStatus.BAD_REQUEST, "NF401", "이미 확인한 알람입니다."),
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "NF404","알람을 찾을 수 없습니다."),


    // JWT
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401", "유효하지 않은 ACCESS 토큰입니다."),
    EXPIRED_MEMBER_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH402", "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH403", "지원되지 않는 JWT 토큰입니다."),
    ILLEGAL_ARGUMENT_TOKEN(HttpStatus.BAD_REQUEST, "AUTH404", "잘못된 JWT 토큰입니다."),
    JWT_NO_USER_INFO(HttpStatus.UNAUTHORIZED, "AUTH405", "토큰에 사용자 정보가 없습니다."),
    JWT_NO_AUTH_INFO(HttpStatus.UNAUTHORIZED, "AUTH406", "권한 정보가 없는 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH407", "유효하지 않은 REFRESH 토큰입니다.");

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
