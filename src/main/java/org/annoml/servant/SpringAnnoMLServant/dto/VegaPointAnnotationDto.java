package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VegaPointAnnotationDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private VegaVisualizationDto visualization;
    @NotNull
    private String annotationType;
    @NotNull
    private String color;
    @NotNull
    private JsonNode note;
    @NotNull
    private JsonNode data;
    @NotNull
    private JsonNode subject;
}
