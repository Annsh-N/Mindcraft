package com.training.backend.entities;

import jakarta.persistence.*;

@Entity
public class QuestionProgress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "moduleProgressID", nullable = false)
    private ModuleProgress moduleProgress;

    @ManyToOne
    @JoinColumn(name = "questionID", nullable = false)
    private Question question;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status;
    
    public QuestionProgress() {
    	
    }

	public QuestionProgress(ModuleProgress moduleProgress, Question question, QuestionStatus status) {
		super();
		this.moduleProgress = moduleProgress;
		this.question = question;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModuleProgress getModuleProgress() {
		return moduleProgress;
	}

	public void setModuleProgress(ModuleProgress moduleProgress) {
		this.moduleProgress = moduleProgress;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuestionStatus getStatus() {
		return status;
	}

	public void setStatus(QuestionStatus status) {
		this.status = status;
	}
    
}
