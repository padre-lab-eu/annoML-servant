package org.annoml.servant.SpringAnnoMLServant.dto;


import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class DiscussionDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private AuthorDto author;
    @NotNull
    private String title;
    @NotNull
    private List<QuestionDto> questions;
    @NotNull
    private VegaVisualizationDto visualization;

}
