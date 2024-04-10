package com.example.gachon.domain.sentence;

import com.example.gachon.domain.history.Histories;
import com.example.gachon.domain.history.HistoriesRepository;
import com.example.gachon.domain.note.Notes;
import com.example.gachon.domain.note.NotesRepository;
import com.example.gachon.domain.sentence.dto.request.SentenceRequestDto;
import com.example.gachon.domain.sentence.dto.response.SentenceResponseDto;
import com.example.gachon.domain.sentenceInfo.SentenceInfo;
import com.example.gachon.domain.sentenceInfo.SentenceInfoRepository;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.SentencesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public void sentOutNote(SentenceRequestDto.SentenceNoteDto sentenceNoteDto, String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(()-> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        Sentences sentence = sentencesRepository.findById(sentenceNoteDto.getSentenceId()).orElseThrow(()-> new SentencesHandler(ErrorStatus.SENTENCE_NOT_FOUND));

        Notes note = Notes.builder()
                .sentence(sentence)
                .user(user)
                .title(sentenceNoteDto.getTitle())
                .content(sentenceNoteDto.getContent())
                .build();

        notesRepository.save(note);
    }
}
