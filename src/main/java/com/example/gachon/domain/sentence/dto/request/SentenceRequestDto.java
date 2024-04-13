package com.example.gachon.domain.sentence.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
public class SentenceRequestDto {

    @Getter
    @Builder
    public static class SentenceDto {
        private String difficulty;
        private String grammar;
        private String count;
    }
}
