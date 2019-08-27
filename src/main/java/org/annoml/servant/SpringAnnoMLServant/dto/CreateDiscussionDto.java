package org.annoml.servant.SpringAnnoMLServant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiscussionDto {
    String reference;
    String url;
    String hash;
    String title;
}
