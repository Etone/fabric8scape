package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.entity.Image;
import info.novatec.fabric8scape.landscaper.service.KubernetesService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
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
    throw new NotYetImplementedException();

  }


  private void createDeployment(DataPool pool) {
    var deployment = getDeployment(pool);
    kubernetesClient.apps().deployments().create(deployment);
  }

  private Deployment getDeployment(DataPool pool) {
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
}
