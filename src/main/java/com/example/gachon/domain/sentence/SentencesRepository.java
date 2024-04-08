package com.example.gachon.domain.sentence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentencesRepository extends JpaRepository<Sentences, Long> {

}
