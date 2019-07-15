package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Question;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class AbstractVisualization extends AbstractEntity {
    @CreatedBy
    @ManyToOne
    @JsonBackReference
    private Author author;
    @Length(max = 255)
    private String title;
    @OneToMany(orphanRemoval = true)
    private List<Question> questions;

    public AbstractVisualization(Author author, @Length(max = 255) String title) {
        this.author = author;
        this.title = title;
        this.questions = new LinkedList<>();
    }


    public AbstractVisualization() { //jpa
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
