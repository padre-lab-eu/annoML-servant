package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class VegaVisualization extends AbstractVisualization {

    private String visualizationUrl;

    private String externalId;

    private String hash;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode schema;

    public VegaVisualization(JsonNode schema) {
        super();
        this.schema = schema;
    }



    public JsonNode getSchema() {
        return schema;
    }

    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }
    public VegaVisualization(String externalId, String visualizationUrl) {
        super();
        this.externalId = externalId;
        this.visualizationUrl = visualizationUrl;
    }

    public String getVisualizationUrl() {
        return visualizationUrl;
    }

    public void setVisualizationUrl(String visualizationUrl) {
        this.visualizationUrl = visualizationUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public VegaVisualization() { // jpa
    }


}
