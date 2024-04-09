package com.example.gachon.domain.word.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WordResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordInfoDto{
        private Long wordId;
        private String name;
        private String meaning;
    }
}
