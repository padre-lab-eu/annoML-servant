package org.annoml.servant.SpringAnnoMLServant.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.dto.VegaVisualizationDto;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.VegaVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.AuthorRepository;
import org.annoml.servant.SpringAnnoMLServant.repository.VisualizationRepository;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VisualizationService {
    private final VisualizationRepository visualizationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VisualizationService(VisualizationRepository visualizationRepository, ModelMapper modelMapper) {
        this.visualizationRepository = visualizationRepository;
        this.modelMapper = modelMapper;
    }

    public List<VegaVisualizationDto> getVegaVisualizations() {
        List<AbstractVisualization> visualizations = this.visualizationRepository.findAll();
        List<VegaVisualizationDto> visualizationDtos = new LinkedList<>();
        for (AbstractVisualization v : visualizations) {
            visualizationDtos.add(convertToDto(v));
        }
        return visualizationDtos;
    }

    public VegaVisualizationDto getVegaVisualization(Long id) {
        AbstractVisualization visualization = this.visualizationRepository.findById(id).get();
        return convertToDto(visualization);
    }

    public VegaVisualization addExternalVisualization( String visualizationId, String visualizationUrl) {
        VegaVisualization visualization = new VegaVisualization(visualizationId, visualizationUrl);
        visualizationRepository.saveAndFlush(visualization);
        return visualization;
    }

    public AbstractVisualization addVegaVisualization(JsonNode schema) {
        VegaVisualization visualization = new VegaVisualization(schema);
        visualizationRepository.saveAndFlush(visualization);
        return visualization;
    }

    // Visualization Helper

    private VegaVisualizationDto convertToDto(AbstractVisualization vegaVisualization) {
        return modelMapper.map(vegaVisualization, VegaVisualizationDto.class);
    }


}
