package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.DataPool;
import info.novatec.fabric8scape.admin.exception.DataPoolNotFoundException;
import info.novatec.fabric8scape.admin.repository.DataPoolRepository;
import info.novatec.fabric8scape.admin.service.DataPoolService;
import info.novatec.fabric8scape.admin.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataPoolServiceImpl implements DataPoolService {

  DataPoolRepository repository;
  MessageService messageService;

  private enum ROUTING_KEYS {
      CREATE("pool.create"),
      DELETE("pool.delete");

    String value;
    ROUTING_KEYS(String value){
      this.value = value;
    }
  }

  public DataPool createNewDataPool(DataPool newPool){
    var pool = repository.save(newPool);
    messageService.sendMessage(ROUTING_KEYS.CREATE.value, pool);
    return pool;
  }

  public void deleteDataPool(int id) {
    repository.deleteById(id);
    messageService.sendMessage(ROUTING_KEYS.DELETE.value, id);
  }

  public Iterable<DataPool> getDataPools(){
    return repository.findAll();
  }

  public DataPool getDataPool(int id){
    var dataPool = repository.findById(id);
    return dataPool.orElseThrow(DataPoolNotFoundException::new);
  }
}
