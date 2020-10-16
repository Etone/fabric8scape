package de.novatec.fabric8scape.registry.configuration;

import de.novatec.fabric8scape.registry.entity.RoutingKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageConfiguration {

  private static final String CREATE_QUEUE_NAME = "create";
  private static final String DELETE_QUEUE_NAME = "delete";

  private static final String EXCHANGE_NAME = "";

  @Bean
  public Queue queueCreate(){
    return new Queue(CREATE_QUEUE_NAME, true);
  }

  @Bean
  public Queue queueDelete(){
    return new Queue(DELETE_QUEUE_NAME, true);
  }


  @Bean
  public Exchange exchange() {
    return new DirectExchange(EXCHANGE_NAME, true, false);
  }

  @Bean
  public Binding bindingCreate(@Qualifier("queueCreate") Queue queue, Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.CREATE.getValue()).noargs();
  }
  @Bean
  public Binding bindingDelete(@Qualifier("queueDelete") Queue queue, Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.DELETE.getValue()).noargs();
  }



  @RabbitListener(queues = CREATE_QUEUE_NAME)
  public static void receiveCreateMessage(Object body){
    log.info("Received CREATE Event with body: {}", body);
  }

  @RabbitListener(queues = DELETE_QUEUE_NAME)
  public static void receiveDeleteMessage(Object body){
    log.info("Received DELETE event with body: {}", body);
  }
}
