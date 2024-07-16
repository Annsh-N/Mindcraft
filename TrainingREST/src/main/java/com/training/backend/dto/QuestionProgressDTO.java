package com.training.backend.dto;

import com.training.backend.entities.QuestionStatus;

public class QuestionProgressDTO {
    private QuestionDTO question;
    private QuestionStatus status;

    public QuestionProgressDTO() {

    }

    public QuestionProgressDTO(QuestionDTO question, QuestionStatus status) {
        this.question = question;
        this.status = status;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public QuestionStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }
}
