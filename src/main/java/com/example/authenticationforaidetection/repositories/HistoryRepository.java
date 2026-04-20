package com.example.authenticationforaidetection.repositories;

import com.example.authenticationforaidetection.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
