package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.service.KubernetesService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;

@AllArgsConstructor
public class KubernetesServiceImpl implements KubernetesService {

  private final KubernetesClient kubernetesClient;

  @Override
  public void deployDataPool(DataPool pool) {
    createDeployment(pool);
  }



  @Override
  public void undeployDataPool(DataPool pool) {
    deleteDeployment(pool);
    deleteService(pool);
    deleteIngressRoute(pool);
  }



  private void createDeployment(DataPool pool) {
    var deployment = getNewDeployment(pool);
    kubernetesClient.apps().deployments().create(deployment);
  }

  private Deployment getNewDeployment(DataPool pool) {
    return new DeploymentBuilder()

        .withNewMetadata()
          .withName(getDeploymentName(pool))
          .addToLabels("id", pool.getId().toString())
          .addToLabels("parent", "landscaper")
        .endMetadata()

        .withNewSpec()
          .withNewTemplate()
            .withNewSpec()
              .addNewContainer()
                .withNewImage(pool.getImage().toString())
              .endContainer()
            .endSpec()
          .endTemplate()
        .endSpec()

        .build();
  }

  private String getDeploymentName(DataPool pool) {
    return "pool-" + pool.getId();
  }


  private void deleteDeployment(DataPool pool) {
    kubernetesClient.apps().deployments().withLabel("parent","landscaper").withLabel("id",pool.getId().toString()).delete();
  }

  private void deleteService(DataPool pool) {
    kubernetesClient.services().withLabel("parent","landscaper").withLabel("id",pool.getId().toString()).delete();
  }

  private void deleteIngressRoute(DataPool pool) {
    // Todo implement
  }

}
