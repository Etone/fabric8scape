package info.novatec.fabric8scape.admin.entity.type;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Image {

  String repository;
  String tag;
}
