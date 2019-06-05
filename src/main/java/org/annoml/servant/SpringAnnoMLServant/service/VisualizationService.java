package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.dto.VegaVisualizationDto;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.VegaVisualization;
import org.annoml.servant.SpringAnnoMLServant.repository.AnnotationRepository;
import org.annoml.servant.SpringAnnoMLServant.repository.VisualizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VisualizationService {
   private final VisualizationRepository visualizationRepository;
   private final AnnotationRepository annotationRepository;
   private final ModelMapper modelMapper;

   @Autowired
    public VisualizationService(VisualizationRepository visualizationRepository, AnnotationRepository annotationRepository, ModelMapper modelMapper) {
        this.visualizationRepository = visualizationRepository;
        this.annotationRepository = annotationRepository;
        this.modelMapper = modelMapper;
    }

    public List<VegaVisualizationDto> getVegaVisualizations() {
     List<AbstractVisualization> visualizations = this.visualizationRepository.findAll();
     List<VegaVisualizationDto> visualizationDtos = new LinkedList<>();
     for (AbstractVisualization v : visualizations) {
         visualizationDtos.add(convertToDto((VegaVisualization) v));
     }
     return visualizationDtos;
    }

    public VegaVisualizationDto getVegaVisualization(Long id) {
       AbstractVisualization visualization = this.visualizationRepository.findById(id).get();
       return convertToDto((VegaVisualization) visualization);
    }

    private VegaVisualizationDto convertToDto(VegaVisualization vegaVisualization) {
       return modelMapper.map(vegaVisualization, VegaVisualizationDto.class);
    }

}
