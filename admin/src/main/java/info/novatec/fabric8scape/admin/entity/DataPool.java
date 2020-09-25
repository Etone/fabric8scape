package info.novatec.fabric8scape.admin.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import info.novatec.fabric8scape.admin.entity.type.Creator;
import info.novatec.fabric8scape.admin.entity.type.Image;
import info.novatec.fabric8scape.admin.entity.type.Structure;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class DataPool {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Integer id;

  @Embedded
  Image image;

  @Embedded
  Creator creator;

  @JsonUnwrapped
  @Embedded
  Structure structure;
}
