package info.novatec.fabric8scape.landscaper.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExchangeKey {

  POOL("POOL"),
  KUBERNETES("KUBERNETES");

  private String value;
}
