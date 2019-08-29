package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
public class AbstractVisualization extends AbstractEntity {

    @Length(max = 255)
    private String description;

    public AbstractVisualization(@Length(max = 255) String description) {
        this.description = description;
    }


    public AbstractVisualization() { //jpa
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
