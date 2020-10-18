package info.novatec.fabric8scape.registry.entity.type;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Creator {
  String environment;
  String system;
  String version;
}
