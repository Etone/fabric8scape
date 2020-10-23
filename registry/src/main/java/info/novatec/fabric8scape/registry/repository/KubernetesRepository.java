package info.novatec.fabric8scape.registry.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface KubernetesRepository {

  Iterable<Integer> getDeployDataPoolIds();
}
