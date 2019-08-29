package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.discussion.Discussion;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    public List<Discussion> findAll();
    public List<Discussion> findAllByPublishedTrue();
    public List<Discussion> findAllByPublishedTrueOrderByCreatedDesc(Pageable pageable);
    public List<Discussion> findAllByPublishedTrueOrderByEditedDesc(Pageable pageable);
    public List<Discussion> findAllByAuthorAndPublishedTrueOrderByCreatedDesc(Author author, Pageable pageable);
    public List<Discussion> findAllByAuthorAndPublishedTrueOrderByEditedDesc(Author author, Pageable pageable);
    public List<Discussion> findAllByVisualization_IdAndPublishedTrue(Long id);
}
