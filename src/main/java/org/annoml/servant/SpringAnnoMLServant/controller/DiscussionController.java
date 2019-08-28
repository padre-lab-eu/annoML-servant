package org.annoml.servant.SpringAnnoMLServant.controller;

import org.annoml.servant.SpringAnnoMLServant.dto.*;
import org.annoml.servant.SpringAnnoMLServant.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;

    @Autowired
    public DiscussionController(DiscussionService discussionService) {
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
            value = "/{id}",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> updateDiscussion(@PathVariable Long id, @RequestBody CreateDiscussionDto createDiscussionDto) {
        return new ResponseEntity<>(discussionService.updateDiscussion(id, createDiscussionDto.getTitle(), createDiscussionDto.getHash()), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> deleteDiscussion(@PathVariable Long id) {
        return new ResponseEntity<>(discussionService.deleteDiscussion(id), HttpStatus.OK);
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
            method = RequestMethod.PUT,
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
            value = "/questions/{questionId}/vote/up",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> upvoteQuestion(@PathVariable Long questionId) {
        return new ResponseEntity<>(discussionService.upVoteQuestion(questionId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/questions/{questionId}/vote/down",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> downvoteQuestion(@PathVariable Long questionId) {
        return new ResponseEntity<>(discussionService.downVoteQuestion(questionId), HttpStatus.OK);
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
            method = RequestMethod.PUT,
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
            value = "/answers/{answerId}/vote/up",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<AnswerDto> upvoteAnswer(@PathVariable Long answerId) {
        return new ResponseEntity<>(discussionService.upVoteAnswer(answerId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/answers/{answerId}/vote/down",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<AnswerDto> downvoteAnswer(@PathVariable Long answerId) {
        return new ResponseEntity<>(discussionService.downVoteAnswer(answerId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/answers/{answerId}/comment", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<CommentDto> addComment(@PathVariable Long answerId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(discussionService.addComment(answerId, commentDto), HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/comments/{commentId}", //
            method = RequestMethod.PUT,
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

    @RequestMapping(
            value = "/comments/{commentId}/vote/up",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<CommentDto> upvoteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(discussionService.upVoteComment(commentId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/comments/{commentId}/vote/down",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<CommentDto> downvoteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(discussionService.downVoteComment(commentId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/questions/{questionId}/highlight/{postId}", //
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> addHighlitedPost(@PathVariable Long questionId, @PathVariable Long postId) {
        return new ResponseEntity<>(discussionService.setHighlightedPost(questionId, postId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/questions/{questionId}/highlight", //
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    ResponseEntity<QuestionDto> removeHighlitedPost(@PathVariable Long questionId) {
        return new ResponseEntity<>(discussionService.removeHighlightedPost(questionId), HttpStatus.OK);
    }

}

