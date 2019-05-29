package org.annoml.servant.SpringAnnoMLServant.model;

import javax.persistence.Entity;

/**
 * Answer entity.
 */
@Entity
public class Answer extends AbstractPost {

    public Answer(String body) {
        super(body);
    }

    public Answer() { // jpa
    }
}