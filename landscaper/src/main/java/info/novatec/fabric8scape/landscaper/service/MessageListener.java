package info.novatec.fabric8scape.landscaper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.novatec.fabric8scape.landscaper.entity.DataPool;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MessageListener {

  private final DataPoolService dataPoolService;
  private final KubernetesService kubernetesService;

  @RabbitListener(queues = { "landscaper.create" })
  public void receiveCreateMessage(String datapool) {
    log.info("Received CREATE Event with body: {}", datapool);

    var pool = deserializeMessage(datapool);
    pool.ifPresent(
        dataPool -> {
          log.info("Pool {} deserialized, writing in DB", dataPool);
          dataPoolService.writeDataPool(dataPool);
        }
    );
  }

  @RabbitListener(queues = { "landscaper.delete" })
  public void receiveDeleteMessage(String id){
    log.info("Received DELETE event with body: {}", id);
    dataPoolService.deleteDataPool(Integer.parseInt(id));
  }

  @RabbitListener(queues = { "landscaper.deploy" })
  public void deployDataPool(String id) {
    log.info("Received DEPLOY event for id: {}", id);
    var dataPool = dataPoolService.getDataPool(Integer.parseInt(id));
    kubernetesService.deployDataPool(dataPool);
  }

  @RabbitListener(queues = { "landscaper.undeploy" })
  public void undeployDataPool(String id) {
    log.info("Received UNDEPLOY event for id: {}", id);
    var datapool = dataPoolService.getDataPool(Integer.parseInt(id));
    kubernetesService.undeployDataPool(datapool);
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
