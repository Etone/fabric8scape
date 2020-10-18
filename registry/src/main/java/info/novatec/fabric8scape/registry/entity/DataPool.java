package info.novatec.fabric8scape.registry.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class DataPool {

  @Id
  Integer id;

  @Embedded
  Creator creator;

}
