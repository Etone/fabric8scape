package info.novatec.fabric8scape.landscaper.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageConfiguration {

  @Bean
  public Queue queueCreate(){
    return new Queue(QueueKey.CREATE.getValue(), true);
  }

  @Bean
  public Queue queueDelete(){
    return new Queue(QueueKey.DELETE.getValue(), true);
  }

  @Bean
  public Queue queueDeploy(){
    return new Queue(QueueKey.DEPLOY.getValue(), true);
  }

  @Bean
  public Queue queueUndeploy(){
    return new Queue(QueueKey.UNDEPLOY.getValue(), true);
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

}
