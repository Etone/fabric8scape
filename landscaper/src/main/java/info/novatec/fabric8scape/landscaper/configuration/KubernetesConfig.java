package info.novatec.fabric8scape.landscaper.configuration;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubernetesConfig {

  @Bean
  public KubernetesClient kubernetesClient(){
    return new DefaultKubernetesClient();
  }
}
