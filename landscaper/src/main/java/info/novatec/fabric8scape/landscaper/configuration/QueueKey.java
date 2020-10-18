package info.novatec.fabric8scape.landscaper.configuration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QueueKey {

  CREATE("create"),
  DELETE("delete"),
  DEPLOY("deploy"),
  UNDEPLOY("undeploy");

  private final String value;

  private final String PREFIX = "landscaper";

  public final String getValue() {
    return PREFIX + "." + value;
  }
}
