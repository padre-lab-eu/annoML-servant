package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {
    private final Author author;

    public MyUser(Author author) {
        super(author.getUsername(), author.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        this.author = author;
    }

    public Author getAuhtor() {
        return author;
    }
}