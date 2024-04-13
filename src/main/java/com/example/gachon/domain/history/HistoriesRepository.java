package com.example.gachon.domain.history;

import com.example.gachon.domain.sentence.Sentences;
import com.example.gachon.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriesRepository extends JpaRepository<Histories, Long> {

    List<Histories> findAllByUser (Users user);
    Optional<Histories> findByUserAndSentence(Users user, Sentences sentences);

}
