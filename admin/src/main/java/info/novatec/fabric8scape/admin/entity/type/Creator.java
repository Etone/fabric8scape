package info.novatec.fabric8scape.admin.entity.type;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Creator implements Serializable {
  String system;
  String environment;
  String version;
}
