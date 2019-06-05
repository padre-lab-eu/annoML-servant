package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.dto.AnswerDto;
import org.annoml.servant.SpringAnnoMLServant.dto.QuestionDto;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.repository.AnswerRepository;
import org.annoml.servant.SpringAnnoMLServant.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DiscussionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscussionService(QuestionRepository questionRepository, AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    public List<QuestionDto> getQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        List<QuestionDto> questionDtos = new LinkedList<>();
        for (Question q : questions) {
            questionDtos.add(convertToDto(q));
        }
        return questionDtos;
    }

    public List<QuestionDto> getUnsolvedQuestions() {
        List<Question> questions = this.questionRepository.listAllUnsolved();
        List<QuestionDto> questionDtos = new LinkedList<>();
        for (Question q : questions) {
            questionDtos.add(convertToDto(q));
        }
        return questionDtos;
    }

    public List<QuestionDto> getUnansweredQuestions() {
        List<Question> questions = this.questionRepository.listAllUnanswered();
        List<QuestionDto> questionDtos = new LinkedList<>();
        for (Question q : questions) {
            questionDtos.add(convertToDto(q));
        }
        return questionDtos;
    }

    @PreAuthorize("hasAuthority(\"USER\")")
    public List<QuestionDto> getByAccountAnsweredQuestions() {
        List<Question> questions = this.questionRepository.listAllAnsweredByUser(getCurrentAuhtor().getId());
        List<QuestionDto> questionDtos = new LinkedList<>();
        for (Question q : questions) {
            questionDtos.add(convertToDto(q));
        }
        return questionDtos;
    }

    private QuestionDto convertToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    private Question convertToEntity(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    private Answer convertToEntity(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }

    private AnswerDto convertToDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    private Author getCurrentAuhtor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("no user?!");
        }
        Author author = ((MyUser) authentication.getPrincipal()).getAuhtor();
        if (author == null) {
            throw new AccessDeniedException("no user?!");
        }
        return author;
    }

}
