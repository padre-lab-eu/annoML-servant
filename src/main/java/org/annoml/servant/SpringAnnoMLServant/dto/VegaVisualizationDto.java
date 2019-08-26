package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VegaVisualizationDto {
    @Id
    @NotNull
    private Long id;
    @JsonProperty
    private String description;
    @JsonProperty
    private JsonNode schema;
    @JsonProperty
    private String url;
    @JsonProperty
    private String reference;
    @JsonProperty
    private String hash;

}
