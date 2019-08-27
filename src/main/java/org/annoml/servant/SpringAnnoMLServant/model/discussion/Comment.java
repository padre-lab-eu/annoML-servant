package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
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

    public Comment(JsonNode body, Author author, List<VegaAnnotation> pointAnnotations, List<VegaAnnotation> rectangleAnnotations, String color) {
        super(body, author, pointAnnotations, rectangleAnnotations, color);
    }

    public Comment() { // jpa

    }
}
