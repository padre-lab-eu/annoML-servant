package org.annoml.servant.SpringAnnoMLServant.model.annotation;

import org.annoml.servant.SpringAnnoMLServant.model.discussion.AbstractPost;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;

import javax.persistence.Entity;


@Entity
public class VegaAnnotation extends AbstractAnnotation {
    private String title;
    private String label;
    private String xCoordinate;
    private String yCoordinate;
    private String color;
    private String subject;

    public VegaAnnotation(Author author, AbstractPost post, AbstractVisualization visualization, String title, String label, String xCoordinate, String yCoordinate, String color, String subject) {
        super(author, post, visualization);
        this.title = title;
        this.label = label;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
