package info.novatec.fabric8scape.registry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No dataPool for given ID found")
public class DataPoolNotFoundException extends RuntimeException{}
