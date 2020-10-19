package info.novatec.fabric8scape.landscaper.service;

import info.novatec.fabric8scape.landscaper.entity.DataPool;
import org.springframework.stereotype.Service;

@Service
public interface KubernetesService {

  void deployDataPool(DataPool pool);
  void undeployDataPool(DataPool pool);
}
