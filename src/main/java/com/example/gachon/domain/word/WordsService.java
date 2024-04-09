package com.example.gachon.domain.word;

import com.example.gachon.domain.word.dto.response.WordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordsService {

    private final WordsRepository wordsRepository;

    public List<WordResponseDto.WordInfoDto> getWordList() {
        List<Words> words = wordsRepository.findAll();
        return words.stream()
                .map(WordsConverter::toWordInfoDto)
                .toList();
    }
}
