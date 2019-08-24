package org.annoml.servant.SpringAnnoMLServant.authentication;

import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorRepository.findAuthorByExternalId(username);

        if (author == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new AuthorUser(author);
        }
    }

    private class AuthorUser extends User {
        private final Author author;

        AuthorUser(Author author) {
            super(author.getExternalId(), author.getExternalId(), AuthorityUtils.createAuthorityList("USER"));
            this.author = author;
        }

        Author getAuthor() {
            return author;
        }

    }
}
