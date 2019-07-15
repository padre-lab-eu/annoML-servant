package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import java.util.List;


/**
 * Comment entity.
 */
@Entity
public class Comment extends AbstractPost {
    @Length(max = 7)
    private String color;

    public Comment(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations, String color) {
        super(body, author, pointAnnotations, rectangleAnnotations);
        this.color = color;
    }

    public Comment() { // jpa

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
