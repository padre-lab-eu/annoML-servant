package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import javax.persistence.Entity;

@Entity
public class ExternalReferenceVisualization extends AbstractVisualization {


    private String reference;

    public ExternalReferenceVisualization(String reference) {
        super();
        this.reference = reference;
    }

    public ExternalReferenceVisualization() { // jpa
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
