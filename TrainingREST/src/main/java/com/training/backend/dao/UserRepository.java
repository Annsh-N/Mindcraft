package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
