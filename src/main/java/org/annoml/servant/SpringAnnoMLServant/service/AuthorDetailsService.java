package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorDetailsService implements UserDetailsService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorDetailsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author account = authorRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }
        return new MyUser(account) {
        };
    }
}
