package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Question entity extending {@link AbstractPost} by a title and a list of answers. Allows setting an answer as the
 * favorite one.
 */
@Entity
@JsonPropertyOrder({"id", "title"})
public class Question extends AbstractPost {
    @NonNull
    @Length(max = 255)
    private String title;
    @OneToMany(orphanRemoval = true)
    private List<Answer> answers;

    @OneToOne
    private AbstractPost highlighted;

    public Question(JsonNode body, Author author, List<VegaAnnotation> pointAnnotations, List<VegaAnnotation> rectangleAnnotations, @NonNull @Length(max = 255) String title, List<Answer> answers, @Length(max = 7) String color) {
        super(body, author, pointAnnotations, rectangleAnnotations, color);
        this.title = title;
        this.answers = answers;
    }


    public Question() { //jpa
    } // jpa



    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public AbstractPost getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(AbstractPost highlighted) {
        this.highlighted = highlighted;
    }
}