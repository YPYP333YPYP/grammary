package com.example.gachon.domain.memo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemosRepository extends JpaRepository<Memos, Long> {
}
