package org.annoml.servant.SpringAnnoMLServant.dto;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private List<AnswerDto> answers;
    @NotNull
    private AnswerDto favorite;
    @NotNull
    private JsonNode body;
    @NotNull
    private AuthorDto author;
    @NotNull
    private Date date;
    @NonNull
    private List<VegaPointAnnotationDto> pointAnnotations;
    @NotNull
    private List<VegaRectangleAnnotationDto> rectangleAnnotations;
    private String color;

}