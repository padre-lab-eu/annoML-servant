package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Answer entity.
 */
@Entity
public class Answer extends AbstractPost {
    @OneToMany(orphanRemoval = true)
    private List<Comment> comments;
    @Length(max = 7)
    private String color;

    public Answer(JsonNode body, Author author, List<VegaAnnotation> pointAnnotations, List<VegaAnnotation> rectangleAnnotations, List<Comment> comments, String color) {
        super(body, author, pointAnnotations, rectangleAnnotations);
        this.comments = comments;
        this.color = color;
    }

    public Answer() { // jpa
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}