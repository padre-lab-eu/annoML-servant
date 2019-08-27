package org.annoml.servant.SpringAnnoMLServant.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.dto.*;
import org.annoml.servant.SpringAnnoMLServant.exception.NotFoundException;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Comment;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.ExternalReferenceVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.ExternalUrlVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final AnnotationRepository annotationRepository;
    private final VisualizationService visualizationService;
    private final ModelMapper modelMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CommentRepository commentRepository, ModelMapper modelMapper, AuthorRepository authorRepository, AnnotationRepository annotationRepository, VisualizationService visualizationService) {
        this.discussionRepository = discussionRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.authorRepository = authorRepository;
        this.annotationRepository = annotationRepository;
        this.visualizationService = visualizationService;
        this.modelMapper = modelMapper;
    }

    public List<DiscussionDto> getDiscussionsByAuthor() {

        List<Discussion> discussions = this.discussionRepository.findAll();
        List<DiscussionDto> discussionDtos = new LinkedList<>();
        for (Discussion d : discussions) {
            discussionDtos.add(convertToDto(d));
        }
        return discussionDtos;

    }

    public DiscussionDto getDiscussion(Long id) {
        Discussion discussion = this.discussionRepository.findById(id).orElseThrow(() -> new NotFoundException("No discussion found"));
        if (discussion.isPublished()) {
            return convertToDto(discussion);
        } else if (getAuthor().equals(discussion.getAuthor())) {
            return convertToDto(discussion);
        } else {
            throw new NotFoundException("Discussion not found");
        }
    }

    public DiscussionDto updateDiscussion(Long id, String title, String hash) {
        Discussion discussion = this.discussionRepository.findById(id).orElseThrow(() -> new NotFoundException("No discussion found"));
        if (getAuthor().equals(discussion.getAuthor())) {
            discussion.setPublished(true);
            discussion.setVisualizationHash(hash);
            discussion.setTitle(title);
            discussionRepository.saveAndFlush(discussion);
            return convertToDto(discussion);
        } else {
            throw new NotFoundException("Discussion not found");
        }
    }

    public DiscussionDto deleteDiscussion(Long id) {
        Discussion discussion = this.discussionRepository.findById(id).orElseThrow(() -> new NotFoundException("No discussion found"));
        if (getAuthor().equals(discussion.getAuthor())) {
            discussionRepository.delete(discussion);
            return convertToDto(discussion);
        } else {
            throw new AccessDeniedException("Not the discussion author");
        }
    }


    public DiscussionDto createDiscussionWithReference(String visualizationId) {
        Author author = getOrCreateAuthor();
        ExternalReferenceVisualization visualization = visualizationService.addExternalVisualizationById(visualizationId);
        Discussion discussion = new Discussion(author, visualization);
        discussionRepository.saveAndFlush(discussion);
        return convertToDto(discussion);
    }

    public DiscussionDto createDiscussionWithUrl(String visualizationUrl) {
        Author author = getOrCreateAuthor();
        ExternalUrlVisualization visualization = visualizationService.addExternalVisualizationByUrl(visualizationUrl);
        Discussion discussion = new Discussion(author, visualization);
        discussionRepository.saveAndFlush(discussion);
        return convertToDto(discussion);
    }

    public DiscussionDto createDiscussionWithImport(JsonNode schema) {
        AbstractVisualization visualization = visualizationService.addVegaVisualization(schema);
        Discussion discussion = new Discussion(this.getAuthor(), visualization);
        discussionRepository.save(discussion);
        return convertToDto(discussion);
    }

    public QuestionDto addQuestion(Long id, QuestionDto questionDto) {
        Discussion discussion = this.discussionRepository.findById(id).orElseThrow(() -> new NotFoundException("No discussion found"));
        List<VegaAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : questionDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : questionDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Question question = new Question(questionDto.getBody(), getOrCreateAuthor(), vegaPointAnnotations, vegaRectangleAnnotations, questionDto.getTitle(), new LinkedList<>(), questionDto.getColor());
        this.questionRepository.saveAndFlush(question);
        discussion.addQuestion(question);
        this.discussionRepository.saveAndFlush(discussion);
        return convertToDto(question);
    }

    public QuestionDto updateQuestion(Long questionId, QuestionDto questionDto) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        if (getAuthor().equals(question.getAuthor())) {
            updateOrCreateNewAnnotations(question.getPointAnnotations(), questionDto.getPointAnnotations());
            updateOrCreateNewAnnotations(question.getRectangleAnnotations(), questionDto.getRectangleAnnotations());
            question.setBody(questionDto.getBody());
            question.setTitle(questionDto.getTitle());
            question.setColor(questionDto.getColor());
            this.questionRepository.saveAndFlush(question);
            return convertToDto(question);
        } else {
            throw new AccessDeniedException("Not the question author");
        }
    }

    public QuestionDto deleteQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        if (getAuthor().equals(question.getAuthor())) {
            this.questionRepository.delete(question);
            return convertToDto(question);
        } else {
            throw new AccessDeniedException("Not the question author");
        }
    }

    public QuestionDto upVoteQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        question.addUpVote(getOrCreateAuthor());
        this.questionRepository.saveAndFlush(question);
        return convertToDto(question);
    }

    public QuestionDto downVoteQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        question.addDownVote(getOrCreateAuthor());
        this.questionRepository.saveAndFlush(question);
        return convertToDto(question);
    }

    public QuestionDto setHighlightedPost(Long questionId, Long postId) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        if (question.getAuthor().equals(getAuthor())) {
            question.getAnswers().forEach(answer -> {
                if (answer.getId().equals(postId)) {
                    question.setHighlighted(answer);
                } else {
                    answer.getComments().forEach(comment -> {
                        if (comment.getId().equals(postId)) {
                            question.setHighlighted(comment);
                        }
                    });
                }
            });
        } else {
            throw new AccessDeniedException("Not the question author");
        }
        return convertToDto(question);
    }

    public QuestionDto removeHighlightedPost(Long questionId) {
        Question question = this.questionRepository.findById(questionId).get();
        if (question.getAuthor().equals(getAuthor())) {
            question.setHighlighted(null);
        } else {
            throw new AccessDeniedException("Not the question author");
        }
        return convertToDto(question);
    }


    public AnswerDto addAnswer(Long questionId, AnswerDto answerDto) {
        Question question = this.questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("No question found"));
        List<VegaAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : answerDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : answerDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Answer answer = new Answer(answerDto.getBody(), getOrCreateAuthor(), vegaPointAnnotations, vegaRectangleAnnotations, new LinkedList<>(), answerDto.getColor());
        this.answerRepository.saveAndFlush(answer);
        question.addAnswer(answer);
        return convertToDto(answer);
    }

    public AnswerDto updateAnswer(Long answerId, AnswerDto answerDto) {
        Answer answer = this.answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No answer found"));
        if (getAuthor().equals(answer.getAuthor())) {
            updateOrCreateNewAnnotations(answer.getPointAnnotations(), answerDto.getPointAnnotations());
            updateOrCreateNewAnnotations(answer.getRectangleAnnotations(), answerDto.getRectangleAnnotations());
            answer.setBody(answerDto.getBody());
            answer.setColor(answer.getColor());
            this.answerRepository.saveAndFlush(answer);
            return convertToDto(answer);
        } else {
            throw new AccessDeniedException("Not the answer author");
        }
    }

    public AnswerDto deleteAnswer(Long answerId) {
        Answer answer = this.answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No answer found"));
        if (getAuthor().equals(answer.getAuthor())) {
            this.answerRepository.delete(answer);
            return convertToDto(answer);
        } else {
            throw new AccessDeniedException("Not the answer author");
        }
    }

    public AnswerDto upVoteAnswer(Long answerId) {
        Answer answer = this.answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No answer found"));
        answer.addUpVote(getOrCreateAuthor());
        return convertToDto(answer);
    }

    public AnswerDto downVoteAnswer(Long answerId) {
        Answer answer = this.answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No answer found"));
        answer.addDownVote(getOrCreateAuthor());
        return convertToDto(answer);
    }

    public CommentDto addComment(Long answerId, CommentDto commentDto) {
        Answer answer = this.answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No answer found"));
        List<VegaAnnotation> vegaPointAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : commentDto.getPointAnnotations()) {
            vegaPointAnnotations.add(convertToEntity(d));
        }
        List<VegaAnnotation> vegaRectangleAnnotations = new LinkedList<>();
        for (VegaAnnotationDto d : commentDto.getRectangleAnnotations()) {
            vegaRectangleAnnotations.add(convertToEntity(d));
        }
        Comment comment = new Comment(commentDto.getBody(), getOrCreateAuthor(), vegaPointAnnotations, vegaRectangleAnnotations, commentDto.getColor());
        this.commentRepository.saveAndFlush(comment);
        answer.addComment(comment);
        return convertToDto(comment);
    }

    public CommentDto updateComment(Long answerId, CommentDto commentDto) {
        Comment comment = this.commentRepository.findById(answerId).orElseThrow(() -> new NotFoundException("No comment found"));
        if (getAuthor().equals(comment.getAuthor())) {
            updateOrCreateNewAnnotations(comment.getPointAnnotations(), commentDto.getPointAnnotations());
            updateOrCreateNewAnnotations(comment.getRectangleAnnotations(), commentDto.getRectangleAnnotations());
            comment.setBody(commentDto.getBody());
            comment.setColor(comment.getColor());
            this.commentRepository.saveAndFlush(comment);
            return convertToDto(comment);
        } else {
            throw new AccessDeniedException("Not the comment author");
        }
    }

    public CommentDto deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("No comment found"));
        if (getAuthor().equals(comment.getAuthor())) {
            this.commentRepository.delete(comment);
            return convertToDto(comment);
        } else {
            throw new AccessDeniedException("Not the question author");
        }
    }

    public CommentDto upVoteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("No comment found"));
        comment.addUpVote(getOrCreateAuthor());
        return convertToDto(comment);
    }

    public CommentDto downVoteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("No comment found"));
        comment.addDownVote(getOrCreateAuthor());
        return convertToDto(comment);
    }


    private void updateOrCreateNewAnnotations(List<VegaAnnotation> annotations, List<VegaAnnotationDto> annotationDtos) {
        for (VegaAnnotationDto d : annotationDtos) {
            Optional<VegaAnnotation> annotation = this.annotationRepository.findById(d.getId());
            if (annotation.isPresent()) {
                VegaAnnotation vegaAnnotation = annotation.get();
                if (annotations.contains(vegaAnnotation)) {
                    vegaAnnotation.setColor(d.getColor());
                    vegaAnnotation.setData(d.getData());
                    vegaAnnotation.setNote(d.getNote());
                    vegaAnnotation.setSubject(d.getSubject());
                } else {
                    throw new IllegalArgumentException("annotation to update is not present");
                }
            } else {
                annotations.add(convertToEntity(d));
            }
        }
    }

    private DiscussionDto convertToDto(Discussion discussion) {
        return modelMapper.map(discussion, DiscussionDto.class);
    }

    private QuestionDto convertToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    private AnswerDto convertToDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    private CommentDto convertToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }


    private VegaAnnotation convertToEntity(VegaAnnotationDto vegaAnnotationDto) {
        return modelMapper.map(vegaAnnotationDto, VegaAnnotation.class);
    }


    private Author getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("no user?!");
        }
        String authorId = authentication.getPrincipal().toString();

        return authorRepository.findAuthorByExternalId(authorId);
    }

    private Author getOrCreateAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("no user?!");
        }
        String authorId = authentication.getPrincipal().toString();

        Author author = authorRepository.findAuthorByExternalId(authorId);
        if (author == null) {
            author = new Author(authorId);
            authorRepository.saveAndFlush(author);
        }
        return author;
    }


}
