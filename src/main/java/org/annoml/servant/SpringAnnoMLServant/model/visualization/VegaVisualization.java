package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

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

    public VegaVisualization(Author author, @Length(max = 255) String title, JsonNode schema) {
        super(author, title);
        this.schema = schema;
    }

    public VegaVisualization() { //jpa
    }

    public JsonNode getSchema() {
        return schema;
    }

    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }
}
