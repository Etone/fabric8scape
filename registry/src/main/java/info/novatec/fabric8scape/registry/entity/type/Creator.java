package info.novatec.fabric8scape.registry.entity.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creator {
  String environment;
  String system;
  String version;
}
