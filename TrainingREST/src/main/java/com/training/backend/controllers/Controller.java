package com.training.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.training.backend.dto.QuestionDTO;
import com.training.backend.dto.QuestionProgressDTO;
import com.training.backend.dto.UserCredentials;
import com.training.backend.dto.UserProgressDTO;
import com.training.backend.entities.User;
import com.training.backend.entities.Module;
import com.training.backend.entities.Question;
import com.training.backend.services.ModuleService;
import com.training.backend.services.ProgressService;
import com.training.backend.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProgressService progressService;

    // @RequestMapping(value="admin/users", method=RequestMethod.GET)
    // public List<User> getUsers() {
    //     return userService.getUsers();
    // }

    // @RequestMapping(value="user/{id}", method=RequestMethod.GET)
    // public User getUser(@PathVariable Long id) {
    //     return userService.getUserById(id);
    // }

    //Return value cant be User. Change to some DTO
    @RequestMapping(value="admin/users", method=RequestMethod.POST)
    public User addUser(@RequestBody UserCredentials userCredentials) {
        User user = new User(userCredentials.getUsername(), userCredentials.getPassword());
        return userService.registerUser(user);
    }

    @RequestMapping(value="admin/users/progress", method = RequestMethod.GET)
    public List<UserProgressDTO> getUsersProgress() {
        return userService.getUsersProgress();
    }

    @RequestMapping(value="admin/users/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value="admin/modules", method=RequestMethod.POST)
    public Module addModule(@RequestBody Module module) {
        module.setQuestions(new ArrayList<Question>());
        return moduleService.addModule(module);
    }

    @RequestMapping(value="admin/modules/{id}", method=RequestMethod.GET)
    public Module getModule(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    @RequestMapping(value="admin/modules", method=RequestMethod.GET)
    public List<String> getModuleNames() {
        return moduleService.getModuleNames();
    }

    @RequestMapping(value="admin/modules/{id}", method=RequestMethod.DELETE)
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }

    @RequestMapping(value="admin/modules/{id}", method=RequestMethod.PUT)
    public Module updateModule(@PathVariable Long id, @RequestBody Module module) {
        module.setModuleId(id);
        return moduleService.updateModule(module);
    }

    @RequestMapping(value="admin/modules/{moduleId}/questions", method=RequestMethod.POST)
    public QuestionDTO addQuestionToModule(@PathVariable Long moduleId, @RequestBody QuestionDTO question) {
        return moduleService.addQuestion(moduleId, question);
    }

    @RequestMapping(value="admin/modules/{moduleId}/questions/{questionId}", method=RequestMethod.DELETE)
    public void deleteQuestion(@PathVariable Long moduleId, @PathVariable Long questionId) {
        moduleService.removeQuestion(moduleId, questionId);
    }

    @RequestMapping(value="admin/modules/{moduleId}/questions/{questionId}", method=RequestMethod.PUT)
    public QuestionDTO updateQuestion(@PathVariable Long moduleId, @PathVariable Long questionId, @RequestBody QuestionDTO question) {
        return moduleService.updateQuestion(moduleId, questionId, question);
    }

    @RequestMapping(value="user/{userId}/modules/{moduleId}/questions", method=RequestMethod.GET)
    public List<QuestionProgressDTO> getQuestions(@PathVariable Long userId, @PathVariable Long moduleId) {
        return moduleService.getUserQuestions(userId, moduleId);
    }

    @RequestMapping(value="user/{userId}/modules/{moduleId}/questions", method=RequestMethod.POST)
    public List<QuestionProgressDTO> submitAnswers(@PathVariable Long userId, @PathVariable Long moduleId, @RequestBody List<QuestionDTO> answers) {
        return progressService.submitAnswers(userId, moduleId, answers);
    }

}
