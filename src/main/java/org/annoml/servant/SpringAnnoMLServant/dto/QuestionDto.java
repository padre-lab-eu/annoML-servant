package org.annoml.servant.SpringAnnoMLServant.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.AbstractPost;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionDto {
    @Id
    @NotNull
    private Long id;
    @DateTimeFormat
    private Date created;
    @DateTimeFormat
    private Date edited;
    @NotNull
    private String title;
    @NotNull
    private List<AnswerDto> answers;
    private String color;
    private AbstractPost highlighted;

    @NotNull
    private JsonNode body;
    private AuthorDto author;
    @NonNull
    private List<VegaPointAnnotationDto> pointAnnotations;
    @NotNull
    private List<VegaRectangleAnnotationDto> rectangleAnnotations;

}