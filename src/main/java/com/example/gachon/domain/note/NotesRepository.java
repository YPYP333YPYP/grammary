package com.example.gachon.domain.note;

import com.example.gachon.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

    List<Notes> findAllByUser(Users user);

    Optional<Notes> findByUserAndSentenceId(Users user, Long sentenceId);
}
