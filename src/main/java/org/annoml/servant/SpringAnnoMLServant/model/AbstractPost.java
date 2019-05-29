package org.annoml.servant.SpringAnnoMLServant.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Superclass for posts containing a body, an author and a date. The corresponding values for author and date get set
 * by JpaAuditing when persisting.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractPost extends AbstractEntity {
    @Column(length = 65000)
    private String body;
    @CreatedBy
    @ManyToOne
    private User author;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    AbstractPost(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    AbstractPost() { // jpa
    }
}