package com.example.gachon.domain.sentence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentencesRepository extends JpaRepository<Sentences, Long> {

    List<Sentences> findAllByGrammarAndDifficulty (String grammar, String difficulty);

    List<Sentences> findAllByGrammar(String grammar);

    List<Sentences> findAllByDifficulty(String difficulty);
}
