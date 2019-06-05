package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class VegaVisualizationDto {
    private Long id;
    @NonNull
    private Author author;
    @NonNull
    @Length(max = 255)
    private String title;
    private List<VegaAnnotationDto> annotations;
    private JsonNode schema;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public List<VegaAnnotationDto> getAnnotations() {
        return annotations;
    }

    @JsonIgnore
    public Author getAuthor() {
        return author;
    }

    @JsonIgnore
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonIgnore
    public void setAnnotations(List<VegaAnnotationDto> annotations) {
        this.annotations = annotations;
    }

    @JsonProperty
    public JsonNode getSchema() {
        return schema;
    }

    @JsonIgnore
    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }
}
