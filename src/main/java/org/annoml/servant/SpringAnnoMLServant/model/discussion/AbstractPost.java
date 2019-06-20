package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Superclass for posts containing a body, an author and a date. The corresponding values for author and date get set
 * by JpaAuditing when persisting.
 */
@Entity
public class AbstractPost extends AbstractEntity {
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode body;
    @CreatedBy
    @ManyToOne
    private Author author;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private List<VegaPointAnnotation> pointAnnotations;

    @OneToMany(orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    private List<VegaRectangleAnnotation> rectangleAnnotations;

    public AbstractPost(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations) {
        this.body = body;
        this.author = author;
        this.pointAnnotations = pointAnnotations;
        this.rectangleAnnotations = rectangleAnnotations;
    }

    public JsonNode getBody() {
        return body;
    }

    public void setBody(JsonNode body) {
        this.body = body;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<VegaPointAnnotation> getPointAnnotations() {
        return pointAnnotations;
    }

    public void setPointAnnotations(List<VegaPointAnnotation> pointAnnotations) {
        this.pointAnnotations = pointAnnotations;
    }

    public List<VegaRectangleAnnotation> getRectangleAnnotations() {
        return rectangleAnnotations;
    }

    public void setRectangleAnnotations(List<VegaRectangleAnnotation> rectangleAnnotations) {
        this.rectangleAnnotations = rectangleAnnotations;
    }

    AbstractPost() { // jpa
    }
}