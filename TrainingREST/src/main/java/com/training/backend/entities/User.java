package com.training.backend.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuleProgress> moduleProgresses;
	
	public User() {
	
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ModuleProgress> getModuleProgresses() {
		return moduleProgresses;
	}

	public void setModuleProgresses(List<ModuleProgress> moduleProgresses) {
		this.moduleProgresses = moduleProgresses;
	}
}
