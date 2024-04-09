package com.example.gachon.domain.word;

import com.example.gachon.domain.word.dto.response.WordResponseDto;

public class WordsConverter {

    public static WordResponseDto.WordInfoDto toWordInfoDto(Words word) {
        return WordResponseDto.WordInfoDto.builder()
                .wordId(word.getId())
                .name(word.getName())
                .meaning(word.getMeaning())
                .build();
    }
}
