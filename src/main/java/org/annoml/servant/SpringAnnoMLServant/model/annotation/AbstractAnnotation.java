package org.annoml.servant.SpringAnnoMLServant.model.annotation;

import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.ManyToOne;

public class AbstractAnnotation extends AbstractEntity {
    @CreatedBy
    @ManyToOne
    private Author author;
}
