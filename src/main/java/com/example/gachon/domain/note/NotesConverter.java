package com.example.gachon.domain.note;

import com.example.gachon.domain.note.dto.response.NoteResponseDto;

public class NotesConverter {

    public static NoteResponseDto.NoteInfoDto toNoteInfoDto(Notes note, String description) {
        return NoteResponseDto.NoteInfoDto.builder()
                .id(note.getId())
                .sentenceId(note.getSentence().getId())
                .name(note.getSentence().getContent())
                .content(description)
                .build();
    }

    public static NoteResponseDto.NotePreviewDto toNotePreviewDto(Notes note) {
        return NoteResponseDto.NotePreviewDto.builder()
                .id(note.getId())
                .name(note.getSentence().getContent())
                .build();
    }
}
