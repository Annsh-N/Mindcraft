package com.training.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "questionType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = McqDTO.class, name = "MCQ"),
    @JsonSubTypes.Type(value = MatchDTO.class, name = "MATCH"),
    @JsonSubTypes.Type(value = OrderDTO.class, name = "ORDER")
})
public class QuestionDTO {
    private Long questionId;
    @JsonProperty("questionType")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private QuestionType questionType;
    private String questionText;

    public QuestionDTO() {
    }

    public QuestionDTO(Long questionId, QuestionType questionType, String questionText) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionText = questionText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public QuestionType getQuesitonType() {
        return questionType;
    }

    public void setQuesitonType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }
}
