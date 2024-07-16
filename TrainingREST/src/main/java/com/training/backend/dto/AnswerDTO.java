package com.training.backend.dto;

public class AnswerDTO {
    private Long questionId;
    private Object answer;

    public AnswerDTO() {
    }

    public AnswerDTO(Long questionId, Object answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }
}
