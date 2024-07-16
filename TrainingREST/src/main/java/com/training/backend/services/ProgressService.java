package com.training.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.backend.dao.ModuleProgressRepository;
import com.training.backend.dao.ModuleRepository;
import com.training.backend.dao.QuestionProgressRepository;
import com.training.backend.dao.UserRepository;
import com.training.backend.dto.AnswerDTO;
import com.training.backend.entities.ModuleProgress;
import com.training.backend.entities.ModuleStatus;
import com.training.backend.entities.OrderQuestion;
import com.training.backend.entities.Question;
import com.training.backend.entities.QuestionProgress;
import com.training.backend.entities.QuestionStatus;
import com.training.backend.entities.User;
import com.training.backend.entities.McqQuestion;
import com.training.backend.entities.MatchQuestion;
import com.training.backend.entities.Module;
import com.training.backend.exceptions.ResourceNotFoundException;

@Service
public class ProgressService {
    @Autowired
    private ModuleProgressRepository moduleProgressRepository;
    @Autowired
    private QuestionProgressRepository questionProgresRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public ModuleProgress updateModuleProgress(ModuleProgress moduleProgress) {
        return moduleProgressRepository.save(moduleProgress);
    }

    public QuestionProgress updateQuestionProgress(QuestionProgress questionProgress) {
        return questionProgresRepository.save(questionProgress);
    }

    public ModuleProgress getModuleProgressById(Long id) {
        return moduleProgressRepository.findById(id).orElse(null);
    }

    public ModuleProgress getModuleProgress(Long userId, Long moduleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        return moduleProgressRepository.findByModuleAndUser(module, user);
    }

    public QuestionProgress getQuestionProgressById(Long id) {
        return questionProgresRepository.findById(id).orElse(null);
    }

    public void deleteModuleProgress(Long id) {
        moduleProgressRepository.deleteById(id);
    }

    public void deleteQuestionProgress(Long id) {
        questionProgresRepository.deleteById(id);
    }

    @Transactional
    public ModuleProgress submitQuestions(Long userId, Long ModuleId, List<AnswerDTO> answers) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Module module = moduleRepository.findById(ModuleId).orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        ModuleProgress moduleProgress = moduleProgressRepository.findByModuleAndUser(module, user);

        List<QuestionProgress> questions = moduleProgress.getQuestionProgresses();

        int correct = 0;

        for (int i = 0; i < answers.size(); i++) {
            QuestionProgress questionProgress = questions.get(i);
            Question question = questionProgress.getQuestion();
            AnswerDTO answer = answers.get(i);
            if (question.getQuestionId() != answer.getQuestionId()) {
                throw new ResourceNotFoundException("Answer does not match question");
            }
            if (question instanceof McqQuestion) {
                McqQuestion mcq = (McqQuestion) question;
                if (mcq.getCorrectAnswer().equals(answer.getAnswer())) {
                    questionProgress.setStatus(QuestionStatus.CORRECT);
                    correct++;
                } else {
                    questionProgress.setStatus(QuestionStatus.INCORRECT);
                }
            } else if (question instanceof MatchQuestion) {
                MatchQuestion match = (MatchQuestion) question;
                if (match.getOptions().equals(answer.getAnswer())) {
                    questionProgress.setStatus(QuestionStatus.CORRECT);
                    correct++;
                } else {
                    questionProgress.setStatus(QuestionStatus.INCORRECT);
                }
            } else if (question instanceof OrderQuestion) {
                OrderQuestion order = (OrderQuestion) question;
                if (order.getOptions().equals(answer.getAnswer())) {
                    questionProgress.setStatus(QuestionStatus.CORRECT);
                    correct++;
                } else {
                    questionProgress.setStatus(QuestionStatus.INCORRECT);
                }
            } else {
                //handle case question is not MCQ, Match or Order
            }
        }

        if (correct == questions.size()) {
            moduleProgress.setStatus(ModuleStatus.COMPLETED);
            int nextModuleIndex = user.getModuleProgresses().indexOf(moduleProgress) + 1;
            if (nextModuleIndex < user.getModuleProgresses().size()) {
                user.getModuleProgresses().get(nextModuleIndex).setStatus(ModuleStatus.UNLOCKED);
            }
        }

        return moduleProgressRepository.save(moduleProgress);
    }

    
}
