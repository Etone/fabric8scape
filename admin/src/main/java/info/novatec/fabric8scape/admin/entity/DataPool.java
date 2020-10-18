package info.novatec.fabric8scape.admin.entity;

import info.novatec.fabric8scape.admin.entity.type.Creator;
import info.novatec.fabric8scape.admin.entity.type.Image;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "data_pool_admin")
public class DataPool implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Integer id;

  @Embedded
  Image image;

  @Embedded
  Creator creator;

}
