package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class AuthorDto {
    private Long id;
    @NonNull
    @JsonProperty
    private Long externalId;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public Long getExternalId() {
        return externalId;
    }
    @JsonIgnore
    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }
}

