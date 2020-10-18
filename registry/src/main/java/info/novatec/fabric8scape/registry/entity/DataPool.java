package info.novatec.fabric8scape.registry.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "data_pool_registry")
public class DataPool {

  @Id
  Integer id;

  @Embedded
  Creator creator;

}
