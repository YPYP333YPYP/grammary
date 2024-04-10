package com.example.gachon.domain.sentence.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
public class SentenceRequestDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SentenceNoteDto {
        private Long sentenceId;
        private String title;
        private String content;
    }
}
