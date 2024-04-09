package com.example.gachon.domain.sentenceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceInfoRepository extends JpaRepository<SentenceInfo, Long> {

    Optional<SentenceInfo> findBySentenceId(Long sentenceId);
}
