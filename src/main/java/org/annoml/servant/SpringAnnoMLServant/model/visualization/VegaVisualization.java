package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class VegaVisualization extends AbstractVisualization {
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode schema;

    public VegaVisualization(JsonNode schema) {
        super();
        this.schema = schema;
    }


    public VegaVisualization() { // jpa
    }

    public JsonNode getSchema() {
        return schema;
    }

    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }


}
