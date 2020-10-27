package info.novatec.fabric8scape.admin.controller;

import info.novatec.fabric8scape.admin.service.KubernetesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/admin/kubernetes")
public class KubernetesController {

  private final KubernetesService kubernetesService;

  @GetMapping("/deploy/{id}")
  public ResponseEntity<Void> deployDataPool(@PathVariable Integer id){
    kubernetesService.deployDataPool(id);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/undeploy/{id}")
  public ResponseEntity<Void> undeployDataPool(@PathVariable Integer id){
    kubernetesService.undeployDataPool(id);
    return ResponseEntity.ok().build();
  }
}
