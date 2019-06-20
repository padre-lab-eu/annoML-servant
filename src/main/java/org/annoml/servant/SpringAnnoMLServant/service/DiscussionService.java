package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.dto.*;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.repository.AnswerRepository;
import org.annoml.servant.SpringAnnoMLServant.repository.DiscussionRepository;
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
    private final DiscussionRepository discussionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.discussionRepository = discussionRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    public List<DiscussionDto> getDiscussions() {
        List<Discussion> discussions = this.discussionRepository.findAll();
        List<DiscussionDto> discussionDtos = new LinkedList<>();
        for (Discussion d : discussions) {
            discussionDtos.add(convertToDto(d));
        }
        return discussionDtos;

    }

    public DiscussionDto getDiscussion(Long id) {
        Discussion discussion = this.discussionRepository.findById(id).get();
        return convertToDto(discussion);
    }

    public QuestionDto addQuestion(Long id, QuestionDto questionDto) {
        Discussion discussion = this.discussionRepository.findById(id).get();
        List<VegaPointAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaPointAnnotationDto d : questionDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaRectangleAnnotationDto d : questionDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Question question = new Question(questionDto.getBody(), getCurrentAuthor(),vegaPointAnnotations, vegaRectangleAnnotations, questionDto.getTitle(), new LinkedList<>(), null, questionDto.getColor());
        this.questionRepository.saveAndFlush(question);
        discussion.addQuestion(question);
        return convertToDto(question);
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
        List<Question> questions = this.questionRepository.listAllAnsweredByUser(getCurrentAuthor().getId());
        List<QuestionDto> questionDtos = new LinkedList<>();
        for (Question q : questions) {
            questionDtos.add(convertToDto(q));
        }
        return questionDtos;
    }

    private QuestionDto convertToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    private DiscussionDto convertToDto(Discussion discussion) { return modelMapper.map(discussion, DiscussionDto.class);
    }

    private Question convertToEntity(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    private Answer convertToEntity(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }

    private VegaPointAnnotation convertToEntity(VegaPointAnnotationDto vegaPointAnnotationDto) {
        return modelMapper.map(vegaPointAnnotationDto, VegaPointAnnotation.class);
    }

    private VegaRectangleAnnotation convertToEntity(VegaRectangleAnnotationDto vegaRectangleAnnotationDto) {
        return modelMapper.map(vegaRectangleAnnotationDto, VegaRectangleAnnotation.class);
    }

    private AnswerDto convertToDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    private Author getCurrentAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("no user?!");
        }
        Author author = ((MyUser) authentication.getPrincipal()).getAuthor();
        if (author == null) {
            throw new AccessDeniedException("no user?!");
        }
        return author;
    }

}
