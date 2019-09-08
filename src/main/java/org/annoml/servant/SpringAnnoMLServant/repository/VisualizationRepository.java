package org.annoml.servant.SpringAnnoMLServant.repository;

import org.annoml.servant.SpringAnnoMLServant.model.visualization.AbstractVisualization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisualizationRepository extends JpaRepository<AbstractVisualization, Long> {
}
