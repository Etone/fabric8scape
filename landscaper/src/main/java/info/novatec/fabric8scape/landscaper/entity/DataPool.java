package info.novatec.fabric8scape.landscaper.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class DataPool {

  @Id
  Integer id;

  @Embedded
  Image image;

}
