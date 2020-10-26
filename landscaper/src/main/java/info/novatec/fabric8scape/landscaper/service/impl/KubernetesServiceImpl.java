package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.service.KubernetesService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.extensions.IngressBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class KubernetesServiceImpl implements KubernetesService {

  public static final String LABEL_KEY_PARENT = "parent";
  public static final String LABEL_VALUE_PARENT = "landscaper";
  public static final String LABEL_KEY_ID = "id";

  private final KubernetesClient kubernetesClient;

  @Override
  public void deployDataPool(DataPool pool) {
    log.info("Creating new Deployment for pool {}", pool);
    createDeployment(pool);

    log.info("Creating new Service for pool {}", pool);
    createService(pool);

    log.info("Adding Ingress rule for pool {}", pool);
    addIngressRule(pool);
  }



  @Override
  public void undeployDataPool(DataPool pool) {
    log.info("Deleting Deployment for pool {}", pool);
    deleteDeployment(pool);

    log.info("Deleting Service for pool {}", pool);
    deleteService(pool);

    log.info("Removing Ingress rule for pool {}", pool);
    deleteIngressRoute(pool);
  }



  private void createDeployment(DataPool pool) {
    var deployment = getNewDeployment(pool);
    kubernetesClient.apps().deployments().create(deployment);
  }

  private void createService(DataPool pool) {
    var service = getNewService(pool);
    kubernetesClient.services().create(service);

  }



  private void addIngressRule(DataPool pool) {
    var ingress = getNewIngress(pool);
  }


  private Deployment getNewDeployment(DataPool pool) {
    return new DeploymentBuilder()

        .withNewMetadata()
          .withName(getDeploymentName(pool))
          .addToLabels("id", pool.getId().toString())
          .addToLabels(LABEL_KEY_PARENT, LABEL_VALUE_PARENT)
        .endMetadata()

        .withNewSpec()
          .withNewSelector()
            .addToMatchLabels(LABEL_KEY_ID, pool.getId().toString())
            .addToMatchLabels(LABEL_KEY_PARENT, LABEL_VALUE_PARENT)
          .endSelector()
          .withNewTemplate()
            .withNewMetadata()
              .withLabels(Map.of(
                  LABEL_KEY_PARENT, LABEL_VALUE_PARENT,
                  LABEL_KEY_ID, pool.getId().toString()))
            .endMetadata()
            .withNewSpec()
              .addNewContainer()
                .withName("container")
                .withNewImage(pool.getImage().toString())
                .addNewPort()
                  .withContainerPort(8080)
                  .withName("http")
                .endPort()
              .endContainer()
            .endSpec()
          .endTemplate()
        .endSpec()

        .build();
  }

  private Service getNewService(DataPool pool) {
    return new ServiceBuilder()

        .withNewMetadata()
          .withName(getServiceName("service-", getDeploymentName(pool)))
        .endMetadata()

        .withNewSpec()
          .withType("ClusterIP")
          .withSelector(Map.of(
              LABEL_KEY_PARENT, LABEL_VALUE_PARENT,
              LABEL_KEY_ID, pool.getId().toString()))
          .addNewPort()
            .withProtocol("TCP")
            .withPort(8080)
          .endPort()
        .endSpec()

        .build();
  }

  private String getServiceName(String resourcePrefix, String deploymentName) {
    return resourcePrefix + deploymentName;
  }

  private Ingress getNewIngress(DataPool pool) {
    return new IngressBuilder()

        .withNewMetadata()
          .withName(getServiceName("ingress-", getDeploymentName(pool)))
          .addToAnnotations("nginx.ingress.kubernetes.io/rewrite-target", "/$2")
          .addToLabels(Map.of(
              LABEL_KEY_PARENT, LABEL_VALUE_PARENT,
              LABEL_KEY_ID, pool.getId().toString()))
        .endMetadata()

        .withNewSpec()
          .addNewRule()
            .withNewHttp()
              .addNewPath()
                .withNewPath(getServiceName("/", pool.getId().toString()) + "(/|$)(.*)")
                .withPathType("Prefix")
                .withNewBackend()
                  .withNewServiceName(getServiceName("service-", getDeploymentName(pool)))
                  .withNewServicePort(8080)
                .endBackend()
              .endPath()
            .endHttp()
          .endRule()
        .endSpec()

        .build();
  }

  private void deleteDeployment(DataPool pool) {
    kubernetesClient.apps()
                    .deployments()
                    .withLabel(LABEL_KEY_PARENT,LABEL_VALUE_PARENT)
                    .withLabel(LABEL_KEY_ID, pool.getId().toString())
                    .delete();
  }

  private void deleteService(DataPool pool) {
    kubernetesClient.services()
                    .withLabel(LABEL_KEY_PARENT, LABEL_VALUE_PARENT)
                    .withLabel(LABEL_KEY_ID, pool.getId().toString())
                    .delete();
  }

  private void deleteIngressRoute(DataPool pool) {
    kubernetesClient.network()
                    .ingresses()
                    .withLabel(LABEL_KEY_PARENT, LABEL_VALUE_PARENT)
                    .withLabel(LABEL_KEY_ID, pool.getId().toString())
                    .delete();
  }

  private String getDeploymentName(DataPool pool) {
    return "pool-" + pool.getId();
  }


}
