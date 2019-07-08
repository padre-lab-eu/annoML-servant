package org.annoml.servant.SpringAnnoMLServant.controller;

import org.annoml.servant.SpringAnnoMLServant.dto.AnswerDto;
import org.annoml.servant.SpringAnnoMLServant.dto.DiscussionDto;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.service.DiscussionService;
import org.annoml.servant.SpringAnnoMLServant.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;

    @Autowired
    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    // Question Controller
    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    ResponseEntity<List<QuestionDto>> getQuestions(@RequestParam(value = "filter", required = false) String filter) {
        if (filter == null) {
            return new ResponseEntity<>(discussionService.getQuestions(), HttpStatus.OK);
        }

        switch (filter) {
            case "unanswered":
                return new ResponseEntity<>(discussionService.getUnansweredQuestions(), HttpStatus.OK);
            case "unsolved":
                return new ResponseEntity<>(discussionService.getUnsolvedQuestions(), HttpStatus.OK);
            case "answeredbyme":
                return new ResponseEntity<>(discussionService.getByAccountAnsweredQuestions(), HttpStatus.OK);
            default:
                return new ResponseEntity<>(discussionService.getQuestions(), HttpStatus.OK);
        }
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



}

