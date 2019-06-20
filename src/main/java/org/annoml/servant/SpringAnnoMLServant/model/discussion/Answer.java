package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Answer entity.
 */
@Entity
public class Answer extends AbstractPost {
    @OneToMany(orphanRemoval = true)
    private List<Answer> answers;

    public Answer(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations, List<Answer> answers) {
        super(body, author, pointAnnotations, rectangleAnnotations);
        this.answers = answers;
    }

    public Answer() { // jpa
    }
}