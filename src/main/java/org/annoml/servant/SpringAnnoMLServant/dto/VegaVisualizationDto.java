package org.annoml.servant.SpringAnnoMLServant.dto;

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
    @NotNull
    private AuthorDto author;
    @NotNull
    private String title;
    @NotNull
    private JsonNode schema;

}
