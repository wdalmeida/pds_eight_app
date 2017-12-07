package eight.ing3.esipe.fr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import eight.ing3.esipe.fr.kafkaServices.consumer.Receiver;
import eight.ing3.esipe.fr.services.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaApplication {

  /*@Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private Sender sender;

  @Value("${kafka.topic.transactionQueue}")
  private String topicName;*/
  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);
  }


  /*@Autowired
  private XmlMapper xmlMapper;

  @Bean
  public XmlMapper getXmlMapper(){
    return new XmlMapper();
  }

  @Bean
  public Boolean test() throws JsonProcessingException {

    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
    transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Date(System.currentTimeMillis())));
    String xml = xmlMapper.writeValueAsString(transactions);


    this.sender.send(topicName, xml);



    return false;
  }*/

}
