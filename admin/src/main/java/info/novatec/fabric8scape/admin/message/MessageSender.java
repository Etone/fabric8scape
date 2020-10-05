package info.novatec.fabric8scape.admin.message;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageSender {

  RabbitMessagingTemplate messagingTemplate;

  private static final String EXCHANGE_KEY = "DATAPOOL";

  public void sendMessage(String routingKey, Object body) {
    messagingTemplate.convertAndSend(EXCHANGE_KEY, routingKey, body);
  }
}
