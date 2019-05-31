package org.annoml.servant.SpringAnnoMLServant.controller;

import org.annoml.servant.SpringAnnoMLServant.service.QAService;
import org.annoml.servant.SpringAnnoMLServant.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    private final QAService qaService;

    @Autowired
    public DiscussionController(QAService qaService) {
        this.qaService = qaService;
    }

    // Question Controller
    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    ResponseEntity<List<QuestionDto>> getQuestions(@RequestParam(value = "filter", required = false) String filter) {
        if (filter == null) {
            return new ResponseEntity<>(qaService.getQuestions(), HttpStatus.OK);
        }

        switch (filter) {
            case "unanswered":
                return new ResponseEntity<>(qaService.getUnansweredQuestions(), HttpStatus.OK);
            case "unsolved":
                return new ResponseEntity<>(qaService.getUnsolvedQuestions(), HttpStatus.OK);
            case "answeredbyme":
                return new ResponseEntity<>(qaService.getByAccountAnsweredQuestions(), HttpStatus.OK);
            default:
                return new ResponseEntity<>(qaService.getQuestions(), HttpStatus.OK);
        }
    }
}

