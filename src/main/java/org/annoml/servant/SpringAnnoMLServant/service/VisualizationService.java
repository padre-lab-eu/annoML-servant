package org.annoml.servant.SpringAnnoMLServant.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.dto.VegaVisualizationDto;
import org.annoml.servant.SpringAnnoMLServant.exception.NotFoundException;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.ExternalReferenceVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.ExternalUrlVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.VegaVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.VisualizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.Response;
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
        AbstractVisualization visualization = this.visualizationRepository.findById(id).orElseThrow(() -> new NotFoundException("No visualization found"));
        return convertToDto(visualization);
    }

    public ExternalReferenceVisualization addExternalVisualizationById(String visualizationId) {
        ExternalReferenceVisualization visualization = new ExternalReferenceVisualization(visualizationId);
        visualizationRepository.saveAndFlush(visualization);
        return visualization;
    }

    public ExternalUrlVisualization addExternalVisualizationByUrl(String visualizationUrl) {
        ExternalUrlVisualization visualization = new ExternalUrlVisualization(visualizationUrl);
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
