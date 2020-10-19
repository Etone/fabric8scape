package info.novatec.fabric8scape.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoutingKey {
    CREATE("pool.create"),
    DELETE("pool.delete");

    String value;
}