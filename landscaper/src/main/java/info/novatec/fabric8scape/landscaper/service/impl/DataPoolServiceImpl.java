package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.exception.DataPoolNotFoundException;
import info.novatec.fabric8scape.landscaper.repository.DataPoolRepository;
import info.novatec.fabric8scape.landscaper.service.DataPoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class DataPoolServiceImpl implements DataPoolService {

  private DataPoolRepository repository;

  @Override
  public void writeDataPool(DataPool pool) {
    log.info("Writing new Pool {} into db", pool);
    repository.save(pool);
  }

  @Override
  public void deleteDataPool(Integer id) {
    log.info("Deleting pool with id: {}", id);
    repository.deleteById(id);
  }

  @Override
  public DataPool getDataPool(Integer id) {
    log.info("Searching for Pool with id: {}", id);
    var pool = repository.findById(id);

    return pool.orElseThrow(DataPoolNotFoundException::new);
  }


}
