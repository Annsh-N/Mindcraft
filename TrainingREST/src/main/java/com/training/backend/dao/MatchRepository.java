package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.MatchQuestion;

public interface MatchRepository extends JpaRepository<MatchQuestion, Long> {

}
