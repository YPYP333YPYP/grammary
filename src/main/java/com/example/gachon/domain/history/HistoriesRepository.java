package com.example.gachon.domain.history;

import com.example.gachon.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriesRepository extends JpaRepository<Histories, Long> {

    List<Histories> findAllByUser (Users user);
}
