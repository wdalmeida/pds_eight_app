package eight.ing3.esipe.fr.dab_approved_transaction.kafka;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Value("${consumer.properties.group_id_config}")
    private static final String GROUPID_KAFKA = "approuvedGroup";

    private static final String TOPIC_KAFKA = "transactionApproved";



    @KafkaListener(topics = TOPIC_KAFKA , group = GROUPID_KAFKA )
    public void listen(String message) {
       logger.debug("Received Messasge in group foo: " + message);
    }
}
