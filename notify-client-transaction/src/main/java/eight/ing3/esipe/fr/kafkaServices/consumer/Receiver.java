package eight.ing3.esipe.fr.kafkaServices.consumer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.esipe.fr.entities.Transaction;
import eight.ing3.esipe.fr.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired
  private XmlMapper xmlMapper;

  @Autowired
  NotificationService notificationService;

  public CountDownLatch getLatch() {
    return latch;
  }



  @KafkaListener(topics = "${kafka.topic.transactionQueue}")
  public void receive(String payload) throws IOException {
    System.out.println("<< " + payload);
    List<Transaction> transactions = xmlMapper.readValue(payload, new TypeReference<List<Transaction>>() {});
    for(Transaction transaction : transactions){
      System.out.println("j'ai recu une nouvelle transaction !!! ");
      System.out.println("la transaction est : " + transaction);
      System.out.println("\n\n");
      notificationService.createNotification(transaction);
//            notificationService.createNotification(transaction.getMontant(), new Timestamp(Long.parseLong(String.valueOf(transaction.getDate()))), transaction.getDetail(), new Integer(String.valueOf(transaction.getMontant())), transaction.getIntitule(), transaction.getIbanrecipient(), transaction.getIntitule(), transaction.getTpe());
      System.out.println("fin");
    }

    latch.countDown();
  }

   /*@KafkaListener(topics = "${kafka.topic.boot}")
   public void receive(ConsumerRecord<?, ?> consumerRecord) {
     LOGGER.info("received payload='{}'", consumerRecord.toString());
     System.out.println("J'ai recu un premier message :slightly_smiling_face: , " + consumerRecord);
     latch.countDown();
   }*/

}