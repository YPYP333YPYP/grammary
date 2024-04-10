package com.example.gachon.domain.note;

import com.example.gachon.domain.memo.Memos;
import com.example.gachon.domain.memo.MemosRepository;
import com.example.gachon.domain.note.dto.request.NoteRequestDto;
import com.example.gachon.domain.note.dto.response.NoteResponseDto;
import com.example.gachon.domain.sentenceInfo.SentenceInfo;
import com.example.gachon.domain.sentenceInfo.SentenceInfoRepository;
import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersConverter;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.NotesHandler;
import com.example.gachon.global.response.exception.handler.SentencesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotesService {

    private final UsersRepository usersRepository;
    private final NotesRepository notesRepository;
    private final SentenceInfoRepository sentenceInfoRepository;
    private final MemosRepository memosRepository;

    NoteResponseDto.NoteInfoDto getNoteInfo(Long noteId) {
        Notes note = notesRepository.findById(noteId).orElseThrow(()-> new NotesHandler(ErrorStatus.NOTE_NOT_FOUND));
        SentenceInfo sentenceInfo = sentenceInfoRepository.findBySentenceId(note.getSentence().getId()).orElseThrow(() ->
                new SentencesHandler(ErrorStatus.SENTENCE_INFO_NOT_FOUND));

        return NotesConverter.toNoteInfoDto(note, sentenceInfo.getDescription());
    }

    public List<NoteResponseDto.NotePreviewDto> getNoteList(String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        List<Notes> notes = notesRepository.findAllByUser(user);

        return notes.stream()
                .map(NotesConverter::toNotePreviewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createNoteMemo(String email, NoteRequestDto.NoteDto noteDto) {
        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        Notes note = notesRepository.findByUserAndSentenceId(user, noteDto.getSentenceId()).orElseThrow(() -> new NotesHandler(ErrorStatus.NOTE_NOT_FOUND));

        Memos memo = Memos.builder()
                .note(note)
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .build();

        memosRepository.save(memo);
    }
}
