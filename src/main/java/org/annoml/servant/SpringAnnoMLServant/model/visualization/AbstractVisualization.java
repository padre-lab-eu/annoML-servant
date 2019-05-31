package org.annoml.servant.SpringAnnoMLServant.model.visualization;

import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.ManyToOne;

public class AbstractVisualization extends AbstractEntity {
    @CreatedBy
    @ManyToOne
    private Author author;
}
