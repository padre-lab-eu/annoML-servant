package org.annoml.servant.SpringAnnoMLServant.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import org.annoml.servant.SpringAnnoMLServant.model.discussion.Answer;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * DataTransferObject containing information related to the question entity.
 * Ignores id, answers, favorite, author and date when mapping from json to dto.
 */
public class QuestionDto {
    private Long id;
    @NonNull
    @Length(max = 255)
    private String title;
    private List<AnswerDto> answers;
    private Answer favorite;
    @NonNull
    private String body;
    private AuthorDto author;
    private Date date;

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public List<AnswerDto> getAnswers() {
        return answers;
    }

    @JsonIgnore
    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    @JsonProperty
    public Answer getFavorite() {
        return favorite;
    }

    @JsonIgnore
    public void setFavorite(Answer favorite) {
        this.favorite = favorite;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty
    public AuthorDto getAuthor() {
        return author;
    }

    @JsonIgnore
    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    @JsonIgnore
    public void setDate(Date date) {
        this.date = date;
    }
}