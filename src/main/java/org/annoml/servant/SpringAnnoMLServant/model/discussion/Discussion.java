package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Discussion extends AbstractEntity {
    @CreatedBy
    @ManyToOne
    @JsonBackReference
    private Author author;
    @Length(max = 255)
    private String title;
    @OneToMany(orphanRemoval = true)
    private List<Question> questions;
    @OneToOne
    private AbstractVisualization visualization;

    public Discussion(Author author, AbstractVisualization visualization) {
        this.author = author;
        this.questions = new LinkedList<>();
        this.visualization = visualization;
    }

    public Discussion(Author author, String title, List<Question> questions, AbstractVisualization visualization) {
        this.author = author;
        this.title = title;
        this.questions = questions;
        this.visualization = visualization;
    }

    public Discussion() { // jpa
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

    public AbstractVisualization getVisualization() {
        return visualization;
    }

    public void setVisualization(AbstractVisualization visualization) {
        this.visualization = visualization;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
