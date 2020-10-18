package info.novatec.fabric8scape.admin.service;

import info.novatec.fabric8scape.admin.entity.ExchangeKey;
import info.novatec.fabric8scape.admin.entity.RoutingKey;

public interface MessageService {

  void sendMessage(ExchangeKey exchange , RoutingKey routingKey, Object body);
}
