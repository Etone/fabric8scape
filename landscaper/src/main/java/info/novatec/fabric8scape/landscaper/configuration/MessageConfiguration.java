package info.novatec.fabric8scape.landscaper.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.service.DataPoolService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageConfiguration {

  private static final String QUEUE_PREFIX = "landscaper";

  private static final String CREATE_QUEUE_NAME = QUEUE_PREFIX + ".create";
  private static final String DELETE_QUEUE_NAME = QUEUE_PREFIX + ".delete";
  private static final String DEPLOY_QUEUE_NAME = QUEUE_PREFIX + ".deploy";
  private static final String UNDEPLOY_QUEUE_NAME = QUEUE_PREFIX + ".undeploy";

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
  public Queue queueDeploy(){
    return new Queue(DEPLOY_QUEUE_NAME, true);
  }

  @Bean
  public Queue queueUndeploy(){
    return new Queue(UNDEPLOY_QUEUE_NAME, true);
  }

  @Bean
  public Exchange exchangePool() {
    return new DirectExchange(ExchangeKey.POOL.getValue(), true, false);
  }

  @Bean
  public Exchange exchangeKubernetes() {
    return new DirectExchange(ExchangeKey.KUBERNETES.getValue(), true, false);
  }

  @Bean
  public Binding bindingCreate(@Qualifier("queueCreate") Queue queue, @Qualifier("exchangePool") Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.CREATE.getValue()).noargs();
  }
  @Bean
  public Binding bindingDelete(@Qualifier("queueDelete") Queue queue, @Qualifier("exchangePool") Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.DELETE.getValue()).noargs();
  }

  @Bean
  public Binding bindingDeploy(@Qualifier("queueDeploy") Queue queue, @Qualifier("exchangeKubernetes") Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.DEPLOY.getValue()).noargs();
  }
  @Bean
  public Binding bindingUndeploy(@Qualifier("queueUndeploy") Queue queue, @Qualifier("exchangeKubernetes") Exchange exchange){
    return BindingBuilder.bind(queue).to(exchange).with(RoutingKeys.UNDEPLOY.getValue()).noargs();
  }

  @RabbitListener(queues = {CREATE_QUEUE_NAME})
  public void receiveCreateMessage(String body) {
    log.info("Received CREATE Event with body: {}", body);

    var pool = deserializeMessage(body);
    pool.ifPresent(
        dataPool -> {
          log.info("Pool {} deserialized, writing in DB", dataPool);
          dataPoolService.writeDataPool(dataPool);
        }
    );
  }

  @RabbitListener(queues = {DELETE_QUEUE_NAME})
  public void receiveDeleteMessage(Integer body){
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
