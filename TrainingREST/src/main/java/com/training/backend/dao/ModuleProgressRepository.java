package com.training.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.backend.entities.Module;
import com.training.backend.entities.ModuleProgress;
import com.training.backend.entities.User;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, Long> {

    public void deleteByModule(Module module);

    public List<ModuleProgress> findByModule(Module module);

    public ModuleProgress findByModuleAndUser(Module module , User user);
}
