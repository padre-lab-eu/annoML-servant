package org.annoml.servant.SpringAnnoMLServant.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AnswerDto {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private List<AnswerDto> answers;
    @NotNull
    private AuthorDto author;
    @NotNull
    private Date date;
}
