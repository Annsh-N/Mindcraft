package com.training.backend.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;
	
	private String text;
	
	@ManyToOne
    @JoinColumn(name = "moduleID", nullable = false)
    private Module module;
	
	public Question() {
		
	}

	public Question(String text, Module module) {
		super();
		this.text = text;
		this.module = module;
	}


	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
