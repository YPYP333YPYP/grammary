package com.example.gachon.domain.memo;

import com.example.gachon.domain.note.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemosRepository extends JpaRepository<Memos, Long> {

    Optional<Memos> findByNote(Notes note);
}
