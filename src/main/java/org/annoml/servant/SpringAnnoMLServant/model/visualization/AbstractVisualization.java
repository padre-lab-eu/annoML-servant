package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.AbstractAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @Cascade(value = CascadeType.ALL)
    @JsonManagedReference
    private List<AbstractAnnotation> annotations;

    public AbstractVisualization(Author author, @Length(max = 255) String title, List<AbstractAnnotation> annotations) {
        this.author = author;
        this.title = title;
        this.annotations = annotations;
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

    public List<AbstractAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AbstractAnnotation> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(AbstractAnnotation annotation) {
        this.annotations.add(annotation);
    }

    public AbstractVisualization() { //jpa
    }
}
