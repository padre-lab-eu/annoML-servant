package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.AbstractAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
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
    private Answer favorite;
    @Length(max = 7)
    private String color;

    public Question(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations, @NonNull @Length(max = 255) String title, List<Answer> answers, Answer favorite, @Length(max = 7) String color) {
        super(body, author, pointAnnotations, rectangleAnnotations);
        this.title = title;
        this.answers = answers;
        this.favorite = favorite;
        this.color = color;
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getFavorite() {
        return favorite;
    }

    public String getTitle() {
        return title;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void setFavorite(Answer favorite) {
        this.favorite = favorite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question() { //jpa
    }
}