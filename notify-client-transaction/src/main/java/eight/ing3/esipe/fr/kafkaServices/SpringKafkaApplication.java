package eight.ing3.esipe.fr.kafkaServices;

import eight.ing3.esipe.fr.kafkaServices.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringKafkaApplication {

  @Autowired
  private Sender sender;

  protected final static String HELLOWORLD_TOPIC = "helloworld.t";


  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);
  }

  @Bean
  public Object Test(){
    sender.send(HELLOWORLD_TOPIC, "test 1");
    sender.send(HELLOWORLD_TOPIC, "test 2");
    sender.send(HELLOWORLD_TOPIC, "test 3");
    sender.send(HELLOWORLD_TOPIC, "test 4");
    return null;
  }
}
