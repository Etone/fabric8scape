package info.novatec.fabric8scape.admin.service;

import info.novatec.fabric8scape.admin.entity.DataPool;

public interface CrudService {

  DataPool createNewDataPool(DataPool newPool);
  void deleteDataPool(int id);
  Iterable<DataPool> getDataPools();
  DataPool getDataPool(int id);
}
