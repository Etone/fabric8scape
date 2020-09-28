package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.DataPool;
import info.novatec.fabric8scape.admin.exception.DataPoolNotFoundException;
import info.novatec.fabric8scape.admin.message.MessageSender;
import info.novatec.fabric8scape.admin.repository.DataPoolRepository;
import info.novatec.fabric8scape.admin.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudServiceImpl implements CrudService {

  DataPoolRepository repository;
  MessageSender messageSender;

  final String ROUTING_KEY_CREATE = "create_pool";
  final String ROUTING_KEY_DELETE = "delete_pool";

  public DataPool createNewDataPool(DataPool newPool){
    var pool = repository.save(newPool);
    messageSender.sendMessage(ROUTING_KEY_CREATE, pool);
    return pool;
  }

  public void deleteDataPool(int id) {
    repository.deleteById(id);
    messageSender.sendMessage(ROUTING_KEY_DELETE, id);
  }

  public Iterable<DataPool> getDataPools(){
    return repository.findAll();
  }

  public DataPool getDataPool(int id){
    var dataPool = repository.findById(id);
    return dataPool.orElseThrow(DataPoolNotFoundException::new);
  }
}
