package info.novatec.fabric8scape.admin.service;

import org.springframework.stereotype.Service;

@Service
public interface KubernetesService {

  void deployDataPool(Integer id);
  void undeployDataPool(Integer id);
}
