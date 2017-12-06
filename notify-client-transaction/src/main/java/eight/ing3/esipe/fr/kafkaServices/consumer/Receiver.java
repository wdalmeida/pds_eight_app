package eight.ing3.esipe.fr.kafkaServices.consumer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.esipe.fr.bean.Transaction;
import eight.ing3.esipe.fr.repositories.NotificationRepository;
import eight.ing3.esipe.fr.services.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired
  private XmlMapper xmlMapper;

  public CountDownLatch getLatch() {
    return latch;
  }


    NotificationService notificationService = null;


    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payload) throws IOException {
      System.out.println("<< " + payload);


        List<Transaction> transactions = xmlMapper.readValue(payload, new TypeReference<List<Transaction>>() {
        });

        for(Transaction transaction : transactions){
            System.out.println("j'ai recu une nouvelle transaction !!! ");
            System.out.println("la transaction est : " + transaction);
            System.out.println("\n\n");

            notificationService.createNotification(1, transaction.getMontant(), new Timestamp(Long.parseLong(String.valueOf(transaction.getDate()))), transaction.getDetail(), 0001, transaction.getIntitule(), transaction.getIbanrecipient(), transaction.getIntitule(), transaction.getTpe());
        }

        latch.countDown();
    }

    /*@KafkaListener(topics = "${kafka.topic.boot}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
      LOGGER.info("received payload='{}'", consumerRecord.toString());
      System.out.println("J'ai recu un premier message :) , " + consumerRecord);
      latch.countDown();
    }*/

}
