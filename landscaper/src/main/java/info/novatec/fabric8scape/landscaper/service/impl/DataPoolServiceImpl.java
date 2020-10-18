package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
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
    repository.save(pool);
  }
}
