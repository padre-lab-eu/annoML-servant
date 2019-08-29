package org.annoml.servant.SpringAnnoMLServant.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.dto.CreateDiscussionDto;
import org.annoml.servant.SpringAnnoMLServant.dto.DiscussionDto;
import org.annoml.servant.SpringAnnoMLServant.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnoMLApiController {
    private final DiscussionService discussionService;

    @Autowired
    public AnnoMLApiController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @RequestMapping(
            value = "/discussions/recent",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<DiscussionDto>> getRecentCreatedDiscussions(@RequestParam("results") int results) {
        return new ResponseEntity<>(discussionService.getRecentCreatedDiscussions(results), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/discussions/active",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<DiscussionDto>> getRecentActiveDiscussions(@RequestParam("results") int results) {
        return new ResponseEntity<>(discussionService.getRecentActiveDiscussions(results), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/author/recent",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<DiscussionDto>> getRecentCreatedDiscussionsByAuthor(@RequestParam("results") int results) {
        return new ResponseEntity<>(discussionService.getRecentCreatedDiscussionsByAuthor(results), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/author/active",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<DiscussionDto>> getRecentActiveDiscussionsByAuthor(@RequestParam("results") int results) {
        return new ResponseEntity<>(discussionService.getRecentActiveDiscussionsByAuthor(results), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/discussions/visualizations/{reference}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<DiscussionDto>> getDiscussionsByVisualizationReference(@PathVariable String reference) {
        return new ResponseEntity<>(discussionService.getCreatedDiscussionsByVisualizationReference(reference), HttpStatus.OK);
    }




    @RequestMapping(
            value = "/create/reference",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> createDiscussionByReference(@RequestBody CreateDiscussionDto createDiscussionDto) {
        return new ResponseEntity<>(discussionService.createDiscussionWithReference(createDiscussionDto.getReference()), HttpStatus.CREATED);

    }


    @RequestMapping(
            value = "/create/url",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> createDiscussionByUrl(@RequestBody CreateDiscussionDto createDiscussionDto) {
        return new ResponseEntity<>(discussionService.createDiscussionWithUrl(createDiscussionDto.getUrl()), HttpStatus.CREATED);

    }


    @RequestMapping(
            value = "/create/import", //
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<DiscussionDto> createDiscussionByImport(@RequestBody JsonNode schema) {
        return new ResponseEntity<>(discussionService.createDiscussionWithImport(schema), HttpStatus.CREATED);
    }


}
