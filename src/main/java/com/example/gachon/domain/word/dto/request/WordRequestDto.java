package com.example.gachon.domain.word.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class WordRequestDto {


    @Getter
    @Builder
    public static class WordDto {
        private String name;
        private String meaning;
    }
}
