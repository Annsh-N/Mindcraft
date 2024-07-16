package com.training.backend.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;
	
	private String name;
	private String content;
	private String description;
	
	@OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions;
	
	public Module() {
		
	}

	public Module(String name, String content, String description, List<Question> questions) {
		super();
		this.name = name;
		this.content = content;
		this.description = description;
		this.questions = questions;
	}


	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
        questions.add(question);
        question.setModule(this);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setModule(null);
    }
}
