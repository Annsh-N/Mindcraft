package com.training.backend.entities;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;

@Entity
public class MatchQuestion extends Question {
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, String> titles;
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, String> options;
	
	public MatchQuestion() {
		
	}

	public MatchQuestion(String text, Module module, Map<String, String> titles, Map<String, String> options) {
		super(text, module);
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
