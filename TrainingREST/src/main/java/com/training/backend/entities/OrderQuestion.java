package com.training.backend.entities;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class OrderQuestion extends Question {
	private List<String> options;
	
	public OrderQuestion() {
		
	}
	
	public OrderQuestion(String text, Module module, List<String> options) {
		super(text, module);
		this.options = options;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
}
