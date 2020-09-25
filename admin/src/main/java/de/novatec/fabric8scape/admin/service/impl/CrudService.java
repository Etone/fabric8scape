package de.novatec.fabric8scape.admin.service.impl;

import de.novatec.fabric8scape.admin.entity.DataPool;
import de.novatec.fabric8scape.admin.repository.DataPoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudService implements de.novatec.fabric8scape.admin.service.CrudService {

  DataPoolRepository repository;

  public DataPool createNewDataPool(DataPool newPool){
    return repository.save(newPool);
  }

  public Iterable<DataPool> getDataPools(){

    return repository.findAll();
  }

  public DataPool deleteDataPool(int id) {
    var dataPool = repository.findById(id);
    repository.deleteById(id);

    return dataPool.orElse(new DataPool());
  }

  public DataPool getDataPool(int id){
    var dataPool = repository.findById(id);
    return dataPool.orElse(new DataPool());
  }
}
