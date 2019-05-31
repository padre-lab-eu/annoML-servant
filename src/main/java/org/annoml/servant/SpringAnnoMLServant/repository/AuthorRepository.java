package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByUsername(String username);
}
