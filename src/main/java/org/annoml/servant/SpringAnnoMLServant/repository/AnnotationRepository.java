package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.annotation.AbstractAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotationRepository extends JpaRepository<AbstractAnnotation, Long> {
    List<AbstractAnnotation> getAbstractAnnotationsByVisualization(Long id);

}
