package com.example.gachon.domain.sentenceInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceInfoRepository extends JpaRepository<SentenceInfo, Long> {

    SentenceInfo findBySentenceId(Long sentenceId);
}
