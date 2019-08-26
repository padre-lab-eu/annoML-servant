package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import javax.persistence.Entity;

@Entity
public class ExternalUrlVisualization extends AbstractVisualization {

    private String url;

    private String hash;

    public ExternalUrlVisualization(String url) {
        super();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ExternalUrlVisualization() { // jpa
    }

}
