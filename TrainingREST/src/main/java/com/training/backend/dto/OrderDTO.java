package com.training.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO extends QuestionDTO {
    private List<String> options;

    public OrderDTO() {
    }

    @JsonCreator
    public OrderDTO(@JsonProperty("questionId") Long questionId,
                    @JsonProperty("questionText") String questionText,
                    @JsonProperty("options") List<String> options) {
        super(questionId, QuestionType.ORDER, questionText);
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
