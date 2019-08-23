package org.annoml.servant.SpringAnnoMLServant.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class CreateDiscussionDTO {
    @NonNull
    String visualizationId;
    @URL
    @NonNull
    String visualizationUrl;
    @NonNull
    String authorId;

    @Override
    public String toString() {
        return "CreateDiscussionDTO{" +
                "id='" + visualizationId + '\'' +
                ", url='" + visualizationUrl + '\'' +
                ", author='" + authorId + '\'' +
                '}';
    }
}
