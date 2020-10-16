package info.novatec.fabric8scape.admin.entity.type;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Image implements Serializable {

  String repository;
  String tag;
}
