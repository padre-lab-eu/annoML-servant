package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.visualization.ExternalReferenceVisualization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExternalReferenceVisualizationRepository extends JpaRepository<ExternalReferenceVisualization, Long> {
    List<ExternalReferenceVisualization> findAllByReferenceOrderByCreatedDesc(String reference);
}
