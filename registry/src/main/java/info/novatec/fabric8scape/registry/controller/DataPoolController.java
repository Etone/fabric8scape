package info.novatec.fabric8scape.registry.controller;

import info.novatec.fabric8scape.registry.entity.DataPool;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import info.novatec.fabric8scape.registry.service.DataPoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registry")
@AllArgsConstructor
public class DataPoolController {

  private DataPoolService dataPoolService;

  @GetMapping("/{id}")
  public ResponseEntity<DataPool> getDataPoolById(@PathVariable("id") Integer id){
    var pool = dataPoolService.findById(id);
    return ResponseEntity.ok(pool);
  }

  @PostMapping()
  public ResponseEntity<Iterable<DataPool>> getDataPoolByCreator(@RequestBody Iterable<Creator> creator) {
    var pools = dataPoolService.filterByCreator(creator);
    return ResponseEntity.ok(pools);
  }

}
