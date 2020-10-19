package info.novatec.fabric8scape.landscaper.service.impl;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import info.novatec.fabric8scape.landscaper.service.KubernetesService;
import org.hibernate.cfg.NotYetImplementedException;

public class KubernetesServiceImpl implements KubernetesService {

  @Override
  public void deployDataPool(DataPool pool) {
    throw new NotYetImplementedException();
  }

  @Override
  public void undeployDataPool(DataPool pool) {
    throw new NotYetImplementedException();

  }
}
