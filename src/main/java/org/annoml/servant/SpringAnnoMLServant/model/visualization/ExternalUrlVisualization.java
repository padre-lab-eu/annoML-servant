package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import javax.persistence.Entity;

@Entity
public class ExternalUrlVisualization extends AbstractVisualization {

    private String url;

    public ExternalUrlVisualization(String url) {
        super();
        this.url = url;
    }

    public ExternalUrlVisualization() { // jpa
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
