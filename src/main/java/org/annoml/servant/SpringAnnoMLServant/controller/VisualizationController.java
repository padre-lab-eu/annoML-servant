package org.annoml.servant.SpringAnnoMLServant.controller;

import org.annoml.servant.SpringAnnoMLServant.dto.VegaVisualizationDto;
import org.annoml.servant.SpringAnnoMLServant.service.VisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provides mapping for the rest api paths
 */
@RestController
@RequestMapping("/visualizations")
public class VisualizationController {
    private final VisualizationService visualizationService;

    @Autowired
    public VisualizationController(VisualizationService visualizationService) {
        this.visualizationService = visualizationService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<List<VegaVisualizationDto>> getVisualizations() {
        return new ResponseEntity<>(visualizationService.getVegaVisualizations(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    ResponseEntity<VegaVisualizationDto> getVisualization(@PathVariable Long id) {
        return new ResponseEntity<>(visualizationService.getVegaVisualization(id), HttpStatus.OK);
    }

}
