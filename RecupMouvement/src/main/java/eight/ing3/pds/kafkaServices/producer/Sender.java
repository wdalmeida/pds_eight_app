package eight.ing3.pds.kafkaServices.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(String topic, String payload) {
    System.out.println(">> " + payload);
    kafkaTemplate.send(topic, payload);
  }
}
