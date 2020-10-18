package info.novatec.fabric8scape.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExchangeKey {

  POOL("POOL"),
  KUBERNETES("KUBERNETES");

  String value;
}
