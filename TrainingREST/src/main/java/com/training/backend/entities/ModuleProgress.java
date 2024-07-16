package com.training.backend.entities;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class ModuleProgress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "moduleID", nullable = false)
    private Module module;

    @Enumerated(EnumType.STRING)
    private ModuleStatus status;

    @OneToMany(mappedBy = "moduleProgress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionProgress> questionProgresses;
    
    public ModuleProgress() {
    	
    }

	public ModuleProgress(User user, Module module, ModuleStatus status, List<QuestionProgress> questionProgresses) {
		super();
		this.user = user;
		this.module = module;
		this.status = status;
		this.questionProgresses = questionProgresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public ModuleStatus getStatus() {
		return status;
	}

	public void setStatus(ModuleStatus status) {
		this.status = status;
	}

	public List<QuestionProgress> getQuestionProgresses() {
		return questionProgresses;
	}

	public void setQuestionProgresses(List<QuestionProgress> questionProgresses) {
		this.questionProgresses = questionProgresses;
	}

	@Override
	public String toString() {
		return "ModuleProgress{" +
			"id=" + id +
			", moduleId=" + (module != null ? module.getModuleId() : "none") +
			", status=" + status +
			'}';
	}
    
}
