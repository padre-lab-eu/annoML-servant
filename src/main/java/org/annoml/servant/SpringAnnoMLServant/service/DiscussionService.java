package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.dto.*;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Comment;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.repository.*;
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
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final AnnotationRepository annotationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CommentRepository commentRepository, ModelMapper modelMapper, AuthorRepository authorRepository, AnnotationRepository annotationRepository) {
        this.discussionRepository = discussionRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.annotationRepository = annotationRepository;
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
        this.questionRepository.save(question);
        discussion.addQuestion(question);
        return convertToDto(question);
    }

    public QuestionDto updateQuestion(Long questionId, QuestionDto questionDto) {
        Question question = this.questionRepository.findById(questionId).get();
        List<VegaPointAnnotation> vegaPointAnnotations = question.getPointAnnotations();
        for (VegaPointAnnotationDto d : questionDto.getPointAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaPointAnnotation annotation = (VegaPointAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaPointAnnotations.add(convertToEntity(d));
            }
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = question.getRectangleAnnotations();
        for (VegaRectangleAnnotationDto d : questionDto.getRectangleAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaRectangleAnnotation annotation = (VegaRectangleAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaRectangleAnnotations.add(convertToEntity(d));
            }
        }
        question.setBody(questionDto.getBody());
        question.setTitle(questionDto.getTitle());
        question.setColor(questionDto.getColor());
        this.questionRepository.saveAndFlush(question);
        return convertToDto(question);
    }

    public QuestionDto deleteQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId).get();
        this.questionRepository.delete(question);
        return convertToDto(question);
    }

    public AnswerDto addAnswer(Long questionId, AnswerDto answerDto) {
        Question question = this.questionRepository.findById(questionId).get();
        List<VegaPointAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaPointAnnotationDto d : answerDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaRectangleAnnotationDto d : answerDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Answer answer = new Answer(answerDto.getBody(), getCurrentAuthor(), vegaPointAnnotations, vegaRectangleAnnotations, new LinkedList<>(), answerDto.getColor());
        this.answerRepository.save(answer);
        question.addAnswer(answer);
        return convertToDto(answer);
    }

    public AnswerDto updateAnswer(Long answerId, AnswerDto answerDto) {
        Answer answer = this.answerRepository.findById(answerId).get();
        List<VegaPointAnnotation> vegaPointAnnotations = answer.getPointAnnotations();
        for (VegaPointAnnotationDto d : answerDto.getPointAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaPointAnnotation annotation = (VegaPointAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaPointAnnotations.add(convertToEntity(d));
            }
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = answer.getRectangleAnnotations();
        for (VegaRectangleAnnotationDto d : answerDto.getRectangleAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaRectangleAnnotation annotation = (VegaRectangleAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaRectangleAnnotations.add(convertToEntity(d));
            }
        }
        answer.setBody(answerDto.getBody());
        answer.setColor(answer.getColor());
        this.answerRepository.saveAndFlush(answer);
        return convertToDto(answer);
    }

    public AnswerDto deleteAnswer(Long answerId) {
        Answer answer = this.answerRepository.findById(answerId).get();
        this.answerRepository.delete(answer);
        return convertToDto(answer);
    }

    public CommentDto addComment(Long answerId, CommentDto commentDto) {
        Answer answer = this.answerRepository.findById(answerId).get();
        List<VegaPointAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaPointAnnotationDto d : commentDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaRectangleAnnotationDto d : commentDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Comment comment = new Comment(commentDto.getBody(), getCurrentAuthor(), vegaPointAnnotations, vegaRectangleAnnotations, commentDto.getColor());
        this.commentRepository.save(comment);
        answer.addComment(comment);
        return convertToDto(comment);
    }

    public CommentDto updateComment(Long answerId, CommentDto commentDto) {
        Comment comment = this.commentRepository.findById(answerId).get();
        List<VegaPointAnnotation> vegaPointAnnotations = comment.getPointAnnotations();
        for (VegaPointAnnotationDto d : commentDto.getPointAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaPointAnnotation annotation = (VegaPointAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaPointAnnotations.add(convertToEntity(d));
            }
        }
        List<VegaRectangleAnnotation> vegaRectangleAnnotations = comment.getRectangleAnnotations();
        for (VegaRectangleAnnotationDto d : commentDto.getRectangleAnnotations()) {
            if (this.annotationRepository.findById(d.getId()).isPresent()) {
                VegaRectangleAnnotation annotation = (VegaRectangleAnnotation) this.annotationRepository.findById(d.getId()).get();
                annotation.setColor(d.getColor());
                annotation.setData(d.getData());
                annotation.setNote(d.getNote());
                annotation.setSubject(d.getSubject());
            } else {
                vegaRectangleAnnotations.add(convertToEntity(d));
            }
        }
        comment.setBody(commentDto.getBody());
        comment.setColor(comment.getColor());
        this.commentRepository.saveAndFlush(comment);
        return convertToDto(comment);
    }

    public CommentDto deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).get();
        this.commentRepository.delete(comment);
        return convertToDto(comment);
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

    private CommentDto convertToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Author getCurrentAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("no user?!");
        }
        // Author author = ((MyUser) authentication.getPrincipal()).getAuthor();
        Author author = authorRepository.findByUsername("user1");
        if (author == null) {
            throw new AccessDeniedException("no user?!");
        }
        return author;
    }

}
