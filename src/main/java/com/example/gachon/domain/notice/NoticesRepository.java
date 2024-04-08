package com.example.gachon.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {

    List<Notices> findAllById(Long noticeId);

}
