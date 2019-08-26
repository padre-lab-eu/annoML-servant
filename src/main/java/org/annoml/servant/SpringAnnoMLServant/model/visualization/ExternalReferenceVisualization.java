package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import javax.persistence.Entity;

@Entity
public class ExternalReferenceVisualization extends AbstractVisualization {


    private String reference;

    private String hash;

    public ExternalReferenceVisualization(String reference) {
        super();
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ExternalReferenceVisualization() { // jpa
    }
}
