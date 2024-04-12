package com.example.gachon.domain.word;

import com.example.gachon.domain.sentence.Sentences;
import com.example.gachon.domain.sentence.SentencesConverter;
import com.example.gachon.domain.sentenceInfo.SentenceInfo;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.domain.word.dto.response.WordResponseDto;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.GeneralHandler;
import com.example.gachon.global.response.exception.handler.SentencesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import com.example.gachon.global.response.exception.handler.WordsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordsService {

    private final WordsRepository wordsRepository;
    private final UsersRepository usersRepository;

    public List<WordResponseDto.WordInfoDto> getWordList() {
        List<Words> words = wordsRepository.findAll();
        return words.stream()
                .map(WordsConverter::toWordInfoDto)
                .toList();
    }

    public WordResponseDto.WordInfoDto getWordInfoByAdmin(String email, Long wordId) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            Words word = wordsRepository.findById(wordId).orElseThrow(()->new WordsHandler(ErrorStatus.WORD_NOT_FOUND));

            return WordsConverter.toWordInfoDto(word);

        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }
    }
}
