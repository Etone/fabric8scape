package de.novatec.fabric8scape.registry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoutingKeys {
    CREATE("pool.create"),
    DELETE("pool.delete");

    String value;
}
