package org.annoml.servant.SpringAnnoMLServant.model.annotation;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;

@Entity
public class VegaPointAnnotation extends VegaAnnotation {
    public static final String TYPE = "POINT";

    public VegaPointAnnotation(String color, JsonNode note, JsonNode data, JsonNode subject) {
        super(color, note, data, subject);
    }

    public VegaPointAnnotation() { //jpa
    }
}
