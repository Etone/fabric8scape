package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.DataPool;
import info.novatec.fabric8scape.admin.entity.RoutingKeys;
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

  public DataPool createNewDataPool(DataPool newPool){
    var pool = repository.save(newPool);
    messageService.sendMessage(RoutingKeys.CREATE.getValue(), pool);
    return pool;
  }

  public void deleteDataPool(int id) {
    repository.deleteById(id);
    messageService.sendMessage(RoutingKeys.DELETE.getValue(), id);
  }

  public Iterable<DataPool> getDataPools(){
    return repository.findAll();
  }

  public DataPool getDataPool(int id){
    var dataPool = repository.findById(id);
    return dataPool.orElseThrow(DataPoolNotFoundException::new);
  }
}
