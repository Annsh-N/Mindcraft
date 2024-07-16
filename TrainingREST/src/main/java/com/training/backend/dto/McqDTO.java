package com.training.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class McqDTO extends QuestionDTO {
    private List<String> options;
    private String answer;

    public McqDTO() {
    }

    @JsonCreator
    public McqDTO(@JsonProperty("questionId") Long questionId, 
                  @JsonProperty("questionText")String questionText, 
                  @JsonProperty("options")List<String> options, 
                  @JsonProperty("answer")String answer) {
        super(questionId, QuestionType.MCQ, questionText);
        this.options = options;
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
