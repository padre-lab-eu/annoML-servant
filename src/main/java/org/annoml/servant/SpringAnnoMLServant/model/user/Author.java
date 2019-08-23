package org.annoml.servant.SpringAnnoMLServant.model.user;

import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Author entity containing the external externalId and a username for backup purposes.
 */
@Entity
public class Author extends AbstractEntity {
    @Column(unique = true)
    private String externalId;

    public Author(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }


    public Author() { // jpa
    }

}