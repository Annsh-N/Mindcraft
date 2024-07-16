package com.training.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.Question;
import com.training.backend.entities.QuestionProgress;

public interface QuestionProgressRepository extends JpaRepository<QuestionProgress, Long> {
    public void deleteByQuestion(Question question);

    public List<QuestionProgress> findByQuestion(Question question);
}
