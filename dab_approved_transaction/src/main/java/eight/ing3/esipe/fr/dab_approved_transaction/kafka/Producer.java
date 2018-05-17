package eight.ing3.esipe.fr.dab_approved_transaction.kafka;

import eight.ing3.esipe.fr.dab_approved_transaction.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${producer.properties.topic}")
    private String topic;


    public void sendMessage(String msg) {
        logger.debug(msg);
        kafkaTemplate.send("transaction_approved", msg);
    }
}
