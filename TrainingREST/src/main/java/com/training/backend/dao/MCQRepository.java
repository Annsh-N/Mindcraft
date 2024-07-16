package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.McqQuestion;

public interface MCQRepository extends JpaRepository<McqQuestion, Long> {

}
