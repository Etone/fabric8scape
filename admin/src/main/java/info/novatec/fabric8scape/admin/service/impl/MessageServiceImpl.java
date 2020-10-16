package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  RabbitMessagingTemplate messagingTemplate;

  private static final String EXCHANGE_KEY = "POOL";

  public void sendMessage(String routingKey, Object body) {
    messagingTemplate.convertAndSend(EXCHANGE_KEY, routingKey, body);
  }
}
