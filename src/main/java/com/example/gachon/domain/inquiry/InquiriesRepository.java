package com.example.gachon.domain.inquiry;

import com.example.gachon.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InquiriesRepository extends JpaRepository<Inquiries, Long> {

    Optional<Inquiries> findById(Long id);
    List<Inquiries> findAllByUser(Users user);

    Inquiries findByUserAndId(Users user, Long id);
}
