package com.example.gachon.domain.sentence;

import com.example.gachon.domain.history.Histories;
import com.example.gachon.domain.history.HistoriesRepository;
import com.example.gachon.domain.note.Notes;
import com.example.gachon.domain.note.NotesRepository;
import com.example.gachon.domain.sentence.dto.response.SentenceResponseDto;
import com.example.gachon.domain.sentenceInfo.SentenceInfo;
import com.example.gachon.domain.sentenceInfo.SentenceInfoRepository;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.GeneralHandler;
import com.example.gachon.global.response.exception.handler.SentencesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SentencesService {

    private final SentencesRepository sentencesRepository;
    private final SentenceInfoRepository sentenceInfoRepository;
    private final UsersRepository usersRepository;
    private final HistoriesRepository historiesRepository;
    private final NotesRepository notesRepository;

    SentenceResponseDto.SentenceInfoDto getSentenceInfo(Long sentenceId) {
        SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(sentenceId).orElseThrow(() -> new SentencesHandler(ErrorStatus.SENTENCE_INFO_NOT_FOUND));
        Sentences sentence = sentencesRepository.findById(sentenceId).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        return SentencesConverter.toSentenceInfoDto(sentence, sentenceInfo);
    }

    SentenceResponseDto.SentenceInfoDto getRecommendSentence(String grammar, String difficulty) {
        List<Sentences> sentences = sentencesRepository.findAllByGrammarAndDifficulty(grammar, difficulty);
        if (sentences.isEmpty()) {
            throw new SentencesHandler(ErrorStatus.SENTENCE_NOT_FOUND);
        }
        int randomIndex = (int)(Math.random() * sentences.size());
        Sentences sentence = sentences.get(randomIndex);
        SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(sentence.getId()).orElseThrow(() -> new SentencesHandler(ErrorStatus.SENTENCE_INFO_NOT_FOUND));

        return SentencesConverter.toSentenceInfoDto(sentence, sentenceInfo);
    }

    @Transactional
    public void inputSentence(String sentence, String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        Sentences sentenceObject = Sentences.builder()
                .content(sentence)
                .type("USER")
                .build();

        sentencesRepository.save(sentenceObject);

        Histories histories = Histories.builder()
                .user(user)
                .sentence(sentenceObject)
                .timestamp(LocalDateTime.now())
                .build();

        historiesRepository.save(histories);

    }

    @Transactional
    public void sentOutNote(Long sentenceId, String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        Sentences sentence = sentencesRepository.findById(sentenceId).orElseThrow(()-> new SentencesHandler(ErrorStatus.SENTENCE_NOT_FOUND));

        Notes note = Notes.builder()
                .sentence(sentence)
                .user(user)
                .build();

        notesRepository.save(note);
    }

    @Transactional
    public void deleteHistory(Long sentenceId, String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        Sentences sentence = sentencesRepository.findById(sentenceId).orElseThrow(()-> new SentencesHandler(ErrorStatus.SENTENCE_NOT_FOUND));
        Histories history = historiesRepository.findByUserAndSentence(user, sentence);

        historiesRepository.delete(history);
    }

    public SentenceResponseDto.SentenceInfoDto getSentenceInfoByAdmin(Long sentenceId, String email) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(sentenceId).orElseThrow(() -> new SentencesHandler(ErrorStatus.SENTENCE_INFO_NOT_FOUND));
            Sentences sentence = sentencesRepository.findById(sentenceId).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));

            return SentencesConverter.toSentenceInfoDto(sentence, sentenceInfo);

        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

    }

    public List<SentenceResponseDto.SentenceInfoDto> getAllSentenceInfoWithQueryByAdmin(String difficulty, String grammar, String email) {
        Users reqUser = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));

        if (Objects.equals(reqUser.getRole(), "ADMIN")) {
            List<Sentences> sentences;
            List<SentenceInfo> sentenceInfos;

            if (!difficulty.isEmpty()) {
                if (!grammar.isEmpty()) {
                    sentences = sentencesRepository.findAllByGrammarAndDifficulty(grammar,difficulty);
                } else {
                    sentences = sentencesRepository.findAllByDifficulty(difficulty);
                }
            } else {
                sentences = sentencesRepository.findAllByGrammar(grammar);
            }

            List<Long> sentenceIdList = sentences.stream()
                    .map(Sentences::getId)
                    .toList();

            sentenceInfos = sentenceIdList.stream()
                    .map(id -> sentenceInfoRepository.findBySentenceId(id).orElseThrow(()-> new SentencesHandler(ErrorStatus.SENTENCE_INFO_NOT_FOUND)))
                    .toList();

            List<SentenceResponseDto.SentenceInfoDto> sentenceInfoDtoList = new ArrayList<>();

            for (int i = 0; i < sentences.size(); i++ ) {
                sentenceInfoDtoList.add(SentencesConverter.toSentenceInfoDto(sentences.get(i),sentenceInfos.get(i)));
            }
            return sentenceInfoDtoList;


        } else {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

    }
}
