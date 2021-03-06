package info.novatec.fabric8scape.registry.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoutingKeys {
    CREATE("pool.create"),
    DELETE("pool.delete");

    String value;
}
