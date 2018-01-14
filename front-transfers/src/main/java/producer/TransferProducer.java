package producer;

import controller.TransferController;
import model.TransferModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static java.time.LocalDate.now;

public class TransferProducer {

    private static Logger logger = Logger.getLogger(TransferProducer.class);

    @Value("${consumer.properties.bootstrap_servers_config}")
    private String bootstrap_servers_config;

    @Value("${consumer.properties.acks_config}")
    private String acks_config;

    @Value("${consumer.properties.retries_config}")
    private String retries_config;

    @Value("${consumer.properties.value_serializer_class_config}")
    private String value_serializer_class_config;

    @Value("${consumer.properties.key_serializer_class_config}")
    private String key_serializer_class_config;

    @Value("${consumer.properties.topic}")
    private String topic;

    public void sendTransfer(TransferModel transfer) {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
        props.put(ProducerConfig.ACKS_CONFIG, acks_config);
        props.put(ProducerConfig.RETRIES_CONFIG, retries_config);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value_serializer_class_config);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key_serializer_class_config);

        try (Producer<String, TransferModel> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<String, TransferModel>(topic, transfer));
            logger.info("Transfer " + transfer.toString() + " submitted");
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        TransferModel test = new TransferModel();
        test.setWording("test");
        test.setAmount(3995);
        test.setBeneficiaryIban("FR768574754");
        test.setSendingIBAN("FR76875686575");
        test.setValueDate(now());
        new TransferProducer().sendTransfer(test);
    }

}
