package org.annoml.servant.SpringAnnoMLServant.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DiscussionDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private AuthorDto author;
    private String title;
    private boolean published;
    @DateTimeFormat
    private Date created;
    @DateTimeFormat
    private Date edited;
    @NotNull
    private List<QuestionDto> questions;
    @NotNull
    private VegaVisualizationDto visualization;
    private String visualizationHash;

}
