package org.annoml.servant.SpringAnnoMLServant.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.dto.*;
import org.annoml.servant.SpringAnnoMLServant.service.AuthorizationService;
import org.annoml.servant.SpringAnnoMLServant.service.DiscussionService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;
    private final AuthorizationService authorizationService;

    @Autowired
    public DiscussionController(DiscussionService discussionService, AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        this.discussionService = discussionService;
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> getDiscussion(@PathVariable Long id) {
        return new ResponseEntity<>(discussionService.getDiscussion(id), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/create", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> createDiscussionByReference(@RequestParam("token") String token, @Valid @RequestBody CreateDiscussionDTO discussionDTO) {
        if (authorizationService.checkAuthorAccessToken(discussionDTO.getAuthorId(), token)) {
            return new ResponseEntity<>(discussionService.createDiscussion(discussionDTO.getVisualizationId(), discussionDTO.getVisualizationUrl(), discussionDTO.getAuthorId()), HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "/import", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> createDiscussionByImport(@RequestBody JsonNode schema) {
        return new ResponseEntity<>(discussionService.createDiscussionByImport(schema), HttpStatus.CREATED);
    }


    @RequestMapping(
            value = "/{id}/question", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> addQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(discussionService.addQuestion(id, questionDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/questions/{questionId}", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(discussionService.updateQuestion(questionId, questionDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/questions/{questionId}", //
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> deleteQuestion(@PathVariable Long questionId) {
        return new ResponseEntity<>(discussionService.deleteQuestion(questionId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/questions/{questionId}/answer", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<AnswerDto> addAnswer(@PathVariable Long questionId, @RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(discussionService.addAnswer(questionId, answerDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/answers/{answerId}", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<AnswerDto> updateAnswer(@PathVariable Long answerId, @RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(discussionService.updateAnswer(answerId, answerDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/answers/{answerId}", //
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    ResponseEntity<AnswerDto> deleteAnswer(@PathVariable Long answerId) {
        return new ResponseEntity<>(discussionService.deleteAnswer(answerId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/answers/{answerId}/comment", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<CommentDto> addAnswer(@PathVariable Long answerId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(discussionService.addComment(answerId, commentDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/comments/{commentId}", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(discussionService.updateComment(commentId, commentDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/comments/{commentId}", //
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    ResponseEntity<CommentDto> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(discussionService.deleteComment(commentId), HttpStatus.OK);
    }


}

