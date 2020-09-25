package info.novatec.fabric8scape.admin.service.impl;

import info.novatec.fabric8scape.admin.entity.DataPool;
import info.novatec.fabric8scape.admin.exception.DataPoolNotFoundException;
import info.novatec.fabric8scape.admin.repository.DataPoolRepository;
import info.novatec.fabric8scape.admin.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudServiceImpl implements CrudService {

  DataPoolRepository repository;

  public DataPool createNewDataPool(DataPool newPool){
    return repository.save(newPool);
  }

  public void deleteDataPool(int id) {
    repository.deleteById(id);
  }

  public Iterable<DataPool> getDataPools(){
    return repository.findAll();
  }

  public DataPool getDataPool(int id){
    var dataPool = repository.findById(id);
    return dataPool.orElseThrow(DataPoolNotFoundException::new);
  }
}
