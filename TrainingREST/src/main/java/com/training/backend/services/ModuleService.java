package com.training.backend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.backend.dao.ModuleProgressRepository;
import com.training.backend.dao.ModuleRepository;
import com.training.backend.dao.QuestionProgressRepository;
import com.training.backend.dao.QuestionRepository;
import com.training.backend.dao.UserRepository;
import com.training.backend.dto.MatchDTO;
import com.training.backend.dto.McqDTO;
import com.training.backend.dto.OrderDTO;
import com.training.backend.dto.QuestionDTO;
import com.training.backend.dto.QuestionProgressDTO;
import com.training.backend.dto.QuestionType;
import com.training.backend.entities.Question;
import com.training.backend.entities.QuestionProgress;
import com.training.backend.entities.QuestionStatus;
import com.training.backend.entities.User;
import com.training.backend.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.training.backend.entities.MatchQuestion;
import com.training.backend.entities.McqQuestion;
import com.training.backend.entities.Module;
import com.training.backend.entities.ModuleProgress;
import com.training.backend.entities.ModuleStatus;
import com.training.backend.entities.OrderQuestion;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModuleProgressRepository moduleProgressRepository;
    @Autowired
    private QuestionProgressRepository questionProgressRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    public List<String> getModuleNames() {
        List<Module> modules =  moduleRepository.findAll();
        List<String> moduleNames = new ArrayList<>();
        for (Module module : modules) {
            moduleNames.add(module.getName());
        }
        return moduleNames;
    }

    @Transactional
    public Module addModule(Module module) {
        moduleRepository.save(module);
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getModuleProgresses().isEmpty()) {
                if (user.getModuleProgresses().get(user.getModuleProgresses().size()).getStatus() == ModuleStatus.COMPLETED) {
                    user.getModuleProgresses().add(new ModuleProgress(user, module, ModuleStatus.UNLOCKED, new ArrayList<QuestionProgress>()));
                } else {
                    user.getModuleProgresses().add(new ModuleProgress(user, module, ModuleStatus.LOCKED, new ArrayList<QuestionProgress>()));
                }
            } else {
                user.getModuleProgresses().add(new ModuleProgress(user, module, ModuleStatus.UNLOCKED, new ArrayList<QuestionProgress>()));
            }     
        }
        userRepository.saveAll(users);
        return module;
    }

    public Module updateModule(Module module) {
        return moduleRepository.save(module);
    }

    @Transactional
    public void deleteModule(Long moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        // Optimized fetching of module progresses directly related to the module
        List<ModuleProgress> progresses = moduleProgressRepository.findByModule(module);
        for (ModuleProgress progress : progresses) {
            ModuleProgress nextProgress = findNextProgress(progress);
            if (nextProgress != null && progress.getStatus() == ModuleStatus.UNLOCKED) {
                nextProgress.setStatus(ModuleStatus.UNLOCKED);
                moduleProgressRepository.save(nextProgress);  // Save immediately if needed
            }
        }
        moduleProgressRepository.flush();

        entityManager.clear();

        moduleProgressRepository.deleteAll(progresses);
        moduleRepository.delete(module);
    }

    private ModuleProgress findNextProgress(ModuleProgress currentProgress) {
        User user = currentProgress.getUser();
        List<ModuleProgress> progresses = user.getModuleProgresses();
        int currentIndex = progresses.indexOf(currentProgress);
        if (currentIndex != -1 && currentIndex < progresses.size() - 1) {
            return progresses.get(currentIndex + 1);
        }
        return null;
    }

    @Transactional
    public QuestionDTO addQuestion(Long moduleId, QuestionDTO question) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        Question newQuestion;
        if (question.getQuesitonType() == QuestionType.MCQ) {
            McqDTO mcqDTO = (McqDTO) question;
            newQuestion = new McqQuestion(mcqDTO.getQuestionText(), module, mcqDTO.getOptions(), mcqDTO.getAnswer());
        } else if (question.getQuesitonType() == QuestionType.MATCH) {
            MatchDTO matchDTO  = (MatchDTO) question;
            newQuestion = new MatchQuestion(matchDTO.getQuestionText(), module, matchDTO.getTitles(), matchDTO.getOptions());
        } else if (question.getQuesitonType() == QuestionType.ORDER) {
            OrderDTO orderDTO = (OrderDTO) question;
            newQuestion = new OrderQuestion(orderDTO.getQuestionText(), module, orderDTO.getOptions());
        } else {
            throw new ResourceNotFoundException("Question Type not found");
        }
        module.addQuestion(newQuestion);
        List<ModuleProgress> moduleProgresses = moduleProgressRepository.findByModule(module);
        for (ModuleProgress moduleProgress : moduleProgresses) {
            moduleProgress.getQuestionProgresses().add(new QuestionProgress(moduleProgress, newQuestion, QuestionStatus.UNANSWERED));
            moduleProgress.setStatus(ModuleStatus.UNLOCKED);
        }
        moduleProgressRepository.saveAll(moduleProgresses);
        moduleRepository.save(module);
        return question;
    }

    @Transactional
    public void removeQuestion(Long moduleId, Long questionId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        questionProgressRepository.deleteByQuestion(question);
        module.removeQuestion(question);
        moduleRepository.save(module);
    }

    @Transactional
    public QuestionDTO updateQuestion(Long moduleId, Long questionId, QuestionDTO questionDTO) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

        int questionIndex = -1;
        for (int i = 0; i < module.getQuestions().size(); i++) {
            if (module.getQuestions().get(i).getQuestionId().equals(questionId)) {
                questionIndex = i;
                break;
            }
        }

        if (questionIndex == -1) {
            throw new ResourceNotFoundException("Question not found in module");
        }

        Question newQuestion;
        if (questionDTO.getQuesitonType() == QuestionType.MCQ) {
            McqDTO mcqDTO = (McqDTO) questionDTO;
            newQuestion = new McqQuestion(mcqDTO.getQuestionText(), module, mcqDTO.getOptions(), mcqDTO.getAnswer());
        } else if (questionDTO.getQuesitonType() == QuestionType.MATCH) {
            MatchDTO matchDTO  = (MatchDTO) questionDTO;
            newQuestion = new MatchQuestion(matchDTO.getQuestionText(), module, matchDTO.getTitles(), matchDTO.getOptions());
        } else if (questionDTO.getQuesitonType() == QuestionType.ORDER) {
            OrderDTO orderDTO = (OrderDTO) questionDTO;
            newQuestion = new OrderQuestion(orderDTO.getQuestionText(), module, orderDTO.getOptions());
        } else {
            throw new ResourceNotFoundException("Question Type not found");
        }
        module.getQuestions().add(questionIndex, newQuestion);
        questionRepository.save(newQuestion);
        moduleRepository.save(module);
        moduleRepository.flush();

        Question currQuestion = questionRepository.findDistinctByQuestionId(questionId);

        List<QuestionProgress> questionProgresses = questionProgressRepository.findByQuestion(currQuestion);
        for (QuestionProgress questionProgress : questionProgresses) {
            questionProgress.setStatus(QuestionStatus.UNANSWERED);
            questionProgress.setQuestion(newQuestion);
        }

        List<ModuleProgress> moduleProgresses = moduleProgressRepository.findByModule(module);
        for (ModuleProgress moduleProgress : moduleProgresses) {
            if (moduleProgress.getStatus() == ModuleStatus.COMPLETED) {
                moduleProgress.setStatus(ModuleStatus.UNLOCKED);
            }
        }
        
        questionProgressRepository.saveAll(questionProgresses);
        moduleProgressRepository.saveAll(moduleProgresses);
        
        module.getQuestions().remove(questionIndex + 1);

        moduleRepository.save(module);
        
        return questionDTO;
    }

    public List<QuestionProgressDTO> getUserQuestions(Long userId, Long moduleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        ModuleProgress moduleProgress = moduleProgressRepository.findByModuleAndUser(module, user);
        List<QuestionProgress> questionProgresses = moduleProgress.getQuestionProgresses();

        List<QuestionProgressDTO> questions = new ArrayList<>();

        for (QuestionProgress questionProgress : questionProgresses) {
            QuestionDTO questionDTO = new QuestionDTO();
            if (questionProgress.getQuestion() instanceof McqQuestion) {
                McqQuestion mcqQuestion = (McqQuestion) questionProgress.getQuestion();
                questionDTO = new McqDTO(mcqQuestion.getQuestionId(), mcqQuestion.getText() , mcqQuestion.getOptions() , "");
            } else if (questionProgress.getQuestion() instanceof MatchQuestion) {
                MatchQuestion matchQuestion = (MatchQuestion) questionProgress.getQuestion();
                List<String> keys = new ArrayList<>(matchQuestion.getOptions().keySet());
                List<String> values = new ArrayList<>(matchQuestion.getOptions().values());
                Collections.shuffle(values);
                Map<String, String> shuffledOptions = new HashMap<String, String>();
                for (int i = 0; i < keys.size(); i++) {
                    shuffledOptions.put(keys.get(i), values.get(i));
                }
                questionDTO = new MatchDTO(matchQuestion.getQuestionId(), matchQuestion.getText(), matchQuestion.getTitles(), shuffledOptions);
            } else if (questionProgress.getQuestion() instanceof OrderQuestion) {
                OrderQuestion orderQuestion = (OrderQuestion) questionProgress.getQuestion();
                List<String> shuffledOptions = new ArrayList<>(orderQuestion.getOptions());
                Collections.shuffle(shuffledOptions);
                questionDTO = new OrderDTO(orderQuestion.getQuestionId(), orderQuestion.getText(), shuffledOptions);
            } else {
                throw new ResourceNotFoundException("Question Type not found");
            }

            questions.add(new QuestionProgressDTO(questionDTO, questionProgress.getStatus()));
        }

        return questions;
    }

}
