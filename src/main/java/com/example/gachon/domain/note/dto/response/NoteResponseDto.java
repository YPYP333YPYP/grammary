package com.example.gachon.domain.note.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoteResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoteInfoDto{
        private Long id;
        private Long userId;
        private Long sentenceId;
        private String name;
        private String content;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotePreviewDto{
        private Long id;
        private String name;
    }
}
