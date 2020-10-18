package info.novatec.fabric8scape.registry.service.impl;

import info.novatec.fabric8scape.registry.entity.DataPool;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import info.novatec.fabric8scape.registry.exception.DataPoolNotFoundException;
import info.novatec.fabric8scape.registry.repository.DataPoolRepository;
import info.novatec.fabric8scape.registry.service.DataPoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DataPoolServiceImpl implements DataPoolService {

  private final DataPoolRepository repository;

  @Override
  public void writePoolInDataBase(DataPool pool) {
    log.info("Writing new DataPool into DataBase {}", pool);
    repository.save(pool);
  }

  @Override
  public DataPool findById(Integer id) {
    log.info("Try to find DataPool with id: {}", id);
    var pool = repository.findById(id);
    return pool.orElseThrow(DataPoolNotFoundException::new);
  }

  @Override
  public Iterable<DataPool> filterByCreator(Iterable<Creator> creators) {
    return repository.findByCreatorIn(creators);
  }
}
