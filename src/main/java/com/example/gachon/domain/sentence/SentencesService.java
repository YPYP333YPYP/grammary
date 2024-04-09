package com.example.gachon.domain.sentence;

import com.example.gachon.domain.sentence.dto.response.SentenceResponseDto;
import com.example.gachon.domain.sentenceInfo.SentenceInfo;
import com.example.gachon.domain.sentenceInfo.SentenceInfoRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SentencesService {

    private final SentencesRepository sentencesRepository;
    private final SentenceInfoRepository sentenceInfoRepository;

    SentenceResponseDto.SentenceInfoDto getSentenceInfo(Long sentenceId) {
        SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(sentenceId);
        Sentences sentence = sentencesRepository.findById(sentenceId).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        return SentencesConverter.toSentenceInfoDto(sentence, sentenceInfo);
    }

    SentenceResponseDto.SentenceInfoDto getRecommendSentence(String grammar, String difficulty) {
        List<Sentences> sentences = sentencesRepository.findAllByGrammarAndDifficulty(grammar, difficulty);
        int randomIndex = (int)(Math.random() * sentences.size());
        Sentences sentence = sentences.get(randomIndex);
        SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(sentence.getId());

        return SentencesConverter.toSentenceInfoDto(sentence, sentenceInfo);
    }
}
