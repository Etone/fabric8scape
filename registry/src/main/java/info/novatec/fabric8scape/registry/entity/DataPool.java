package info.novatec.fabric8scape.registry.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.novatec.fabric8scape.registry.entity.type.Creator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPool {

  Integer id;
  Creator creator;

}
