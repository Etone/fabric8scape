package de.novatec.fabric8scape.admin.service;

import de.novatec.fabric8scape.admin.entity.DataPool;

public interface CrudService {

  public DataPool createNewDataPool(DataPool newPool);
  public Iterable<DataPool> getDataPools();
  public DataPool deleteDataPool(int id);
  public DataPool getDataPool(int id);
}
