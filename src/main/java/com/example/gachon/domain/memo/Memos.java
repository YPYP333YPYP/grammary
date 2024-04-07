package com.example.gachon.domain.memo;

import com.example.gachon.domain.note.Notes;
import com.example.gachon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memos")
public class Memos extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    private Notes note;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
}
