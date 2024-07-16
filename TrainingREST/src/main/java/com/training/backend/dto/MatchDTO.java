package com.training.backend.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchDTO extends QuestionDTO{
    private Map<String, String> titles;
    private Map<String, String> options;

    public MatchDTO() {
    }

    @JsonCreator
    public MatchDTO(@JsonProperty("questionId") Long questionId,
                    @JsonProperty("questionText") String questionText,
                    @JsonProperty("titles") Map<String, String> titles,
                    @JsonProperty("otpions") Map<String, String> options) {
        super(questionId, QuestionType.MATCH, questionText);
        this.titles = titles;
        this.options = options;
    }

    public Map<String, String> getTitles() {
        return titles;
    }

    public void setTitles(Map<String, String> titles) {
        this.titles = titles;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
