package com.training.backend.entities;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class McqQuestion extends Question {
	private List<String> options;
	private String correctAnswer;
	
	public McqQuestion () {
		
	}

	public McqQuestion(String text, Module module, List<String> options, String correctAnswer) {
		super(text, module);
		this.options = options;
		this.correctAnswer = correctAnswer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
