package info.novatec.fabric8scape.registry.service;

import info.novatec.fabric8scape.registry.entity.DataPool;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import org.springframework.stereotype.Service;

@Service
public interface DataPoolService {

  void writePoolInDataBase(DataPool pool);
  DataPool findById(Integer id);
  Iterable<DataPool> filterByCreator(Iterable<Creator> creators);
  void deleteDataPool(Integer id);
  Iterable<DataPool> getDeployedDataPools();
}
