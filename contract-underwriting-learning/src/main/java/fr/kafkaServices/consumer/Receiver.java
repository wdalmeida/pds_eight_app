package fr.kafkaServices.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.entity.ProductionTransactionEntity;
import fr.repository.ProductionTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import fr.service.MockProductionTransaction;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired
  private XmlMapper xmlMapper;

  @Autowired
  ProductionTransactionRepository productionTransactionRepository;

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.transactionQueue}")
  public void receive(String payload) throws IOException {
    System.out.println("<< " + payload);
    List<ProductionTransactionEntity> productionTransactionEntities = xmlMapper.readValue(payload, new TypeReference<List<ProductionTransactionEntity>>() {});
    for(ProductionTransactionEntity entity : productionTransactionEntities){
      System.out.println("j'ai recu une nouvelle transaction !!! ");
      System.out.println("la transaction est : " + entity);
      System.out.println("\n\n");
      //ProductionTransactionEntity entity1 = new ProductionTransactionEntity();
      productionTransactionRepository.save(entity);
      System.out.println("fin");
    }

    latch.countDown();
  }
}