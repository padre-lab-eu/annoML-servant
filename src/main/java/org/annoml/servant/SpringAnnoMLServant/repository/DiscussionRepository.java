package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findAll();

    List<Discussion> findAllByPublishedTrue();

    List<Discussion> findAllByPublishedTrueOrderByCreatedDesc(Pageable pageable);

    List<Discussion> findAllByPublishedTrueOrderByEditedDesc(Pageable pageable);

    List<Discussion> findAllByAuthorAndPublishedTrueOrderByCreatedDesc(Author author, Pageable pageable);

    List<Discussion> findAllByAuthorAndPublishedTrueOrderByEditedDesc(Author author, Pageable pageable);

    List<Discussion> findAllByVisualization_IdAndPublishedTrue(Long id);
}
