package eight.ing3.esipe.fr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.esipe.fr.bean.DateTransaction;
import eight.ing3.esipe.fr.bean.Transaction;
import eight.ing3.esipe.fr.kafkaServices.producer.Sender;
import eight.ing3.esipe.fr.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringKafkaApplication {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private Sender sender;

  @Autowired
  private XmlMapper xmlMapper;

  @Value("${kafka.topic.helloworld}")
  private String topicName;

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);
  }

  @Bean
  public XmlMapper getXmlMapper(){
    return new XmlMapper();
  }

  @Bean
  public Boolean test() throws JsonProcessingException {

    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Timestamp(System.currentTimeMillis())));
    String xml = xmlMapper.writeValueAsString(transactions);


    this.sender.send(topicName, xml);



    return false;
  }

}
