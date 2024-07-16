package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.Question;



public interface QuestionRepository extends JpaRepository<Question, Long> {

    public Question findDistinctByQuestionId(Long questionId);
}