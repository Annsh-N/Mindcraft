package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}
