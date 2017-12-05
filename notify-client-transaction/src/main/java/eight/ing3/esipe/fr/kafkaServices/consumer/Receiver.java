package eight.ing3.esipe.fr.kafkaServices.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payload) {
      LOGGER.info("received payload='{}'", payload);
      System.out.println("<< " + payload);
      latch.countDown();
    }

    /*@KafkaListener(topics = "${kafka.topic.boot}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
      LOGGER.info("received payload='{}'", consumerRecord.toString());
      System.out.println("J'ai recu un premier message :) , " + consumerRecord);
      latch.countDown();
    }*/

}
