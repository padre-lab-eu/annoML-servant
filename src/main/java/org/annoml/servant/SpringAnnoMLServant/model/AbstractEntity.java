package org.annoml.servant.SpringAnnoMLServant.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Superclass that contains the primary key needed for persisting. All other entities extend this class.
 */
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue()
    private Long id;

    public Long getId() {
        return id;
    }
}