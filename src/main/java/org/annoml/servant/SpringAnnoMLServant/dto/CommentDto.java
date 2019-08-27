package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CommentDto {
    @Id
    @NotNull
    private Long id;
    @DateTimeFormat
    private Date created;
    @DateTimeFormat
    private Date edited;
    @NotNull
    private AuthorDto author;
    @NotNull
    private JsonNode body;
    @NotNull
    private List<VegaAnnotationDto> pointAnnotations;
    @NotNull
    private List<VegaAnnotationDto> rectangleAnnotations;
    @NotNull
    private String color;
    private boolean highlighted;

    private Set<AuthorDto> upVotes;
    private Set<AuthorDto> downVotes;
}
