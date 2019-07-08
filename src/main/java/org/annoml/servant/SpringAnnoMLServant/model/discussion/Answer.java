package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 7)
    private String color;

    public Answer(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations, List<Answer> answers, String color) {
        super(body, author, pointAnnotations, rectangleAnnotations);
        this.answers = answers;
        this.color = color;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Answer() { // jpa
    }
}