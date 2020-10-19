package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.ExchangeKey;
import info.novatec.fabric8scape.admin.entity.RoutingKey;
import info.novatec.fabric8scape.admin.service.KubernetesService;
import info.novatec.fabric8scape.admin.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KubernetesServiceImpl implements KubernetesService {

  private MessageService messageService;

  @Override
  public void deployDataPool(Integer id) {
    messageService.sendMessage(ExchangeKey.KUBERNETES, RoutingKey.DEPLOY, id);
  }

  @Override
  public void undeployDataPool(Integer id) {
    messageService.sendMessage(ExchangeKey.KUBERNETES, RoutingKey.UNDEPLOY, id);
  }
}
