package org.annoml.servant.SpringAnnoMLServant.model.discussion;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaPointAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.VegaRectangleAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

/**
 * Superclass for posts containing a body, an author and a created. The corresponding values for author and created get set
 * by JpaAuditing when persisting.
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date edited;

    @OneToMany
    private Set<Author> upVotes;
    @OneToMany
    private Set<Author> downVotes;


    @OneToMany(orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<VegaPointAnnotation> pointAnnotations;

    @OneToMany(orphanRemoval = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<VegaRectangleAnnotation> rectangleAnnotations;

    public AbstractPost(JsonNode body, Author author, List<VegaPointAnnotation> pointAnnotations, List<VegaRectangleAnnotation> rectangleAnnotations) {
        this.body = body;
        this.author = author;
        this.pointAnnotations = pointAnnotations;
        this.rectangleAnnotations = rectangleAnnotations;

        this.upVotes = new LinkedHashSet<>();
        this.downVotes = new LinkedHashSet<>();


    }

    AbstractPost() { // jpa
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }

    public Set<Author> getUpVotes() {
        return upVotes;
    }

    public void addUpVote(Author author) {
        this.upVotes.add(author);
        this.downVotes.remove(author);
    }

    public Set<Author> getDownVotes() {
        return downVotes;
    }

    public void addDownVote(Author author) {
        this.downVotes.add(author);
        this.upVotes.remove(author);
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
}