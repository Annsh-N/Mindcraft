package com.training.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.backend.dao.ModuleRepository;
import com.training.backend.dao.UserRepository;
import com.training.backend.dto.UserProgressDTO;
import com.training.backend.entities.ModuleProgress;
import com.training.backend.entities.ModuleStatus;
import com.training.backend.entities.Question;
import com.training.backend.entities.QuestionProgress;
import com.training.backend.entities.QuestionStatus;
import com.training.backend.entities.User;
import com.training.backend.entities.Module;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public User registerUser(User user) {
        List<ModuleProgress> moduleProgresses = new ArrayList<>();
        List<Module> modules = moduleRepository.findAll();
        for (int i = 0; i < modules.size(); i++) {
            ModuleProgress moduleProgress = new ModuleProgress();
            moduleProgress.setUser(user);
            moduleProgress.setModule(modules.get(i));
            if (i == 0) {
                moduleProgress.setStatus(ModuleStatus.UNLOCKED);
            } else {
                moduleProgress.setStatus(ModuleStatus.LOCKED);
            }
            List<QuestionProgress> questionProgresses = new ArrayList<>();
            for (Question question : modules.get(i).getQuestions()) {
                questionProgresses.add(new QuestionProgress(moduleProgress, question, QuestionStatus.UNANSWERED));
            }
            moduleProgress.setQuestionProgresses(questionProgresses);
            moduleProgresses.add(moduleProgress);
        }
        user.setModuleProgresses(moduleProgresses);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserProgressDTO> getUsersProgress() {
        List<User> users = userRepository.findAll();
        List<UserProgressDTO> usersProgress = new ArrayList<>();
        for (User user : users) {
            List<ModuleProgress> moduleProgresses = user.getModuleProgresses();
            int correct = 0;
            int total = 0;
            for (ModuleProgress moduleProgress : moduleProgresses) {
                List<QuestionProgress> questionProgresses = moduleProgress.getQuestionProgresses();
                for (QuestionProgress questionProgress : questionProgresses) {
                    if (questionProgress.getStatus() == QuestionStatus.CORRECT) {
                        correct++;
                    }
                    total++;
                }

            }
            float progress = (float) correct / total;
            usersProgress.add(new UserProgressDTO(user.getUserId(), user.getUsername(), progress));
        }
        return usersProgress;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
