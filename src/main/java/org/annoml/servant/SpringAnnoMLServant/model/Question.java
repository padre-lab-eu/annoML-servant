package org.annoml.servant.SpringAnnoMLServant.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
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

    public Question(String title, String body) {
        super(body);
        answers = new ArrayList<>();
        this.title = title;

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

    private Question() { // jpa
        answers = new ArrayList<>();
    }
}