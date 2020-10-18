package info.novatec.fabric8scape.registry.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.novatec.fabric8scape.registry.entity.DataPool;
import info.novatec.fabric8scape.registry.service.DataPoolService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MessageConfiguration {

  private static final String QUEUE_PREFIX = "registry";

  private static final String CREATE_QUEUE_NAME = QUEUE_PREFIX + ".create";
  private static final String DELETE_QUEUE_NAME = QUEUE_PREFIX + ".delete";

  private static final String EXCHANGE_NAME = "POOL";

  private final DataPoolService dataPoolService;

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
  public void receiveCreateMessage(String body) {
    log.info("Received CREATE Event with body: {}", body);

    var pool = deserializeMessage(body);

    pool.ifPresent(
        (dataPool) -> {
        //call Service to enter in DB
         log.info("Deserialized DataPool: {}", dataPool);
         dataPoolService.writePoolInDataBase(dataPool);
        }
    );

  }

  @RabbitListener(queues = DELETE_QUEUE_NAME)
  public void receiveDeleteMessage(Object body){
    log.info("Received DELETE event with body: {}", body);
  }

  private static Optional<DataPool> deserializeMessage(String message) {
    try {
      var pool = new ObjectMapper().readValue(message, DataPool.class);
      return Optional.of(pool);
    } catch (JsonProcessingException e) {
      log.error("Could not deserialize message {}. Exception: {}", message, e.getMessage());
    }
    return Optional.empty();
  }

}
