package org.annoml.servant.SpringAnnoMLServant.controller;

import org.annoml.servant.SpringAnnoMLServant.service.VisualizationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides mapping for the rest api paths
 */
@RestController
@RequestMapping("/visualization")
public class VisualizationController {
    private final VisualizationService visService;
}
