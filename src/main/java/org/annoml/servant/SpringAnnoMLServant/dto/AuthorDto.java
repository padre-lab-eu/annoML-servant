package org.annoml.servant.SpringAnnoMLServant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class AuthorDto {
    private Long id;
    @NonNull
    @JsonProperty
    private String externalId;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getExternalId() {
        return externalId;
    }

    @JsonIgnore
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}

