package info.novatec.fabric8scape.admin.service;

public interface MessageService {

  void sendMessage(String routingKey, Object body);
}
