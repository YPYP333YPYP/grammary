package com.example.gachon.domain.inquiry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class InquiryResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InquiryInfoDto{
        private Long inquiryId;
        private String requestMessage;
        private String responseMessage;
        private LocalDateTime requestTimestamp;
        private LocalDateTime responseTimestamp;
    }
}

