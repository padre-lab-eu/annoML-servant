package org.annoml.servant.SpringAnnoMLServant.model.annotation;

import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.AbstractPost;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class AbstractAnnotation extends AbstractEntity {
    @CreatedBy
    @ManyToOne
    private Author author;
    @ManyToOne
    private AbstractPost post;
    @ManyToOne
    private AbstractVisualization visualization;

    public AbstractAnnotation(Author author, AbstractPost post, AbstractVisualization visualization) {
        this.author = author;
        this.post = post;
        this.visualization = visualization;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public AbstractPost getPost() {
        return post;
    }

    public void setPost(AbstractPost post) {
        this.post = post;
    }

    public AbstractVisualization getVisualization() {
        return visualization;
    }

    public void setVisualization(AbstractVisualization visualization) {
        this.visualization = visualization;
    }
}
