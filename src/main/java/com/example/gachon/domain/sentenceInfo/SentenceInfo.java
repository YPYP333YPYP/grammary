package com.example.gachon.domain.sentenceInfo;

import com.example.gachon.domain.sentence.Sentences;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sentence_info")
public class SentenceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence_id", unique = true)
    private Sentences sentence;

    @Column
    private String subject;

    @Column
    private String verb;

    @Column
    private String object;

    @Column
    private String complement;

    @Column(name = "adverbial_phrase")
    private String adverbialPhrase;

    @Column
    private String conjunction;

    @Column(name = "relative_clause")
    private String relativeClause;

    @Column(name = "indirect_object")
    private String indirectObject;

    @Column(name = "direct_object")
    private String directObject;

    @Column
    private String description;

    @Column
    private String interpretation;
}

