package org.annoml.servant.SpringAnnoMLServant.model.annotation;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
public class VegaAnnotation extends AbstractEntity {
    private String color;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode note;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode data;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode subject;



    public VegaAnnotation(String color, JsonNode note, JsonNode data, JsonNode subject) {
        this.color = color;
        this.note = note;
        this.data = data;
        this.subject = subject;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public JsonNode getNote() {
        return note;
    }

    public void setNote(JsonNode note) {
        this.note = note;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getSubject() {
        return subject;
    }

    public void setSubject(JsonNode subject) {
        this.subject = subject;
    }

    public VegaAnnotation() { // JPA
    }

}
