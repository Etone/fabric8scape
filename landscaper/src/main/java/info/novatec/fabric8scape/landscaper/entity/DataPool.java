package info.novatec.fabric8scape.landscaper.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "data_pool_landscaper")
public class DataPool {

  @Id
  Integer id;

  @Embedded
  Image image;

}
