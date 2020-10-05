package info.novatec.fabric8scape.admin.controller;

import info.novatec.fabric8scape.admin.entity.DataPool;
import info.novatec.fabric8scape.admin.service.CrudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class CrudController {

  CrudService crudService;

  @GetMapping("/")
  public Iterable<DataPool> getDataPools() {
    log.info("GET Request, listing all DataPools in Database");
    return crudService.getDataPools();
  }

  @GetMapping("/{id}")
  public DataPool getDataPool(@PathVariable("id") int id) {
    log.info("GET Request, listing DataPool with id {}", id);
    return crudService.getDataPool(id);
  }

  @PutMapping("/")
  public DataPool createNewPool(@RequestBody DataPool newPool){
    log.info("PUT Request, creating new DataPool {}", newPool);
    return crudService.createNewDataPool(newPool);
  }

  @DeleteMapping("/{id}")
  public void deleteDataPool(@PathVariable("id") int id){
    log.info("DELETE Request, deleting DataPool with id {} form database", id);
    crudService.deleteDataPool(id);
  }
}
