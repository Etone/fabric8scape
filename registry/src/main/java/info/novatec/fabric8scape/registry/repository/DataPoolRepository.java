package info.novatec.fabric8scape.registry.repository;

import info.novatec.fabric8scape.registry.entity.DataPool;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DataPoolRepository extends CrudRepository<DataPool, Integer> {

  Iterable<DataPool> findByCreator(Creator creator);
  Iterable<DataPool> findByCreatorIn(Iterable<Creator> creators);
}
