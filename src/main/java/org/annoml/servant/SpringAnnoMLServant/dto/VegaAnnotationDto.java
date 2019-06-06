package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.AbstractPost;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;

public class VegaAnnotationDto {
    private Long id;
    @NonNull
    private Author author;
    private AbstractPost post;
    private AbstractVisualization visualization;
    private String color;
    private JsonNode note;
    private JsonNode data;
    private JsonNode subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public AbstractPost getPost() {
        return post;
    }

    public void setPost(AbstractPost post) {
        this.post = post;
    }

    @JsonIgnore
    public AbstractVisualization getVisualization() {
        return visualization;
    }

    public void setVisualization(AbstractVisualization visualization) {
        this.visualization = visualization;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public JsonNode getNote() {
        return note;
    }

    public void setNote(JsonNode note) {
        this.note = note;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getSubject() {
        return subject;
    }

    public void setSubject(JsonNode subject) {
        this.subject = subject;
    }
}
