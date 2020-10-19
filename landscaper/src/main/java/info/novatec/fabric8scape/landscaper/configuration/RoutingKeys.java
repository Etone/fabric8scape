package info.novatec.fabric8scape.landscaper.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoutingKeys {
    CREATE("pool.create"),
    DELETE("pool.delete"),
    DEPLOY("k8s.deploy"),
    UNDEPLOY("k8s.undeploy");

    String value;
}
