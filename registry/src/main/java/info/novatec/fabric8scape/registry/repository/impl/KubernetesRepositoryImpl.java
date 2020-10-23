package info.novatec.fabric8scape.registry.repository.impl;

import info.novatec.fabric8scape.registry.repository.KubernetesRepository;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KubernetesRepositoryImpl implements KubernetesRepository {

  KubernetesClient kubernetes;

  @Override
  public Iterable<Integer> getDeployDataPoolIds() {
    return kubernetes.apps()
              .deployments()
              .withLabel("parent", "landscaper") //only deployments from landscaper
              .list()
              .getItems()
              .stream()
              .map(deployment ->
                       Integer.parseInt(deployment.getMetadata().getLabels().get("id"))) //get IDs from those deployments
              .collect(Collectors.toList());
  }
}
