package com.example.gachon.domain.note.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoteRequestDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoteDto {
        private Long sentenceId;
        private String title;
        private String content;
    }
}
