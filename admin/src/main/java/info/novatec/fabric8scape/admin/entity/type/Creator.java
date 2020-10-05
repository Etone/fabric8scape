package info.novatec.fabric8scape.admin.entity.type;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Creator {
  String system;
  String environment;
  String version;
}
