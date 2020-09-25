package info.novatec.fabric8scape.admin.repository;

import info.novatec.fabric8scape.admin.entity.DataPool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPoolRepository extends CrudRepository<DataPool, Integer> {}
