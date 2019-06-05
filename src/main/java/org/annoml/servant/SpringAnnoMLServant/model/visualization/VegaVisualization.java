package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import com.fasterxml.jackson.databind.JsonNode;
import org.annoml.servant.SpringAnnoMLServant.model.annotation.AbstractAnnotation;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.List;

@Entity
public class VegaVisualization extends AbstractVisualization {
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode schema;

    public VegaVisualization(Author author, @Length(max = 255) String title, List<AbstractAnnotation> annotations, JsonNode schema) {
        super(author, title, annotations);
        this.schema = schema;
    }

    public JsonNode getSchema() {
        return schema;
    }

    public void setSchema(JsonNode schema) {
        this.schema = schema;
    }

    public VegaVisualization() { //jpa
    }
}
