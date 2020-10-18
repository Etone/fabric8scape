package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.ExchangeKey;
import info.novatec.fabric8scape.admin.entity.RoutingKey;
import info.novatec.fabric8scape.admin.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  RabbitMessagingTemplate messagingTemplate;

  public void sendMessage(ExchangeKey exchange, RoutingKey routingKey, Object body) {
    messagingTemplate.convertAndSend(exchange.getValue(), routingKey.getValue(), body);
  }
}
