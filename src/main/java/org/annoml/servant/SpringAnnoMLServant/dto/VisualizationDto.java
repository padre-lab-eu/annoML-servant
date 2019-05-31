package org.annoml.servant.SpringAnnoMLServant.dto;

import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

public class VisualizationDto {
    private Long id;
    @NonNull
    @Length(max = 255)
    private String title;
}
