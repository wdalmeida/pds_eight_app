package producer;

import controller.TransferController;
import dto.TransferDto;
import model.TransferModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

import static java.time.LocalDate.now;

@Component
public class TransferProducer {

    private static Logger logger = Logger.getLogger(TransferProducer.class);

    @Value("${producer.properties.bootstrap_servers_config}")
    private String bootstrap_servers_config;

    @Value("${producer.properties.acks_config}")
    private String acks_config;

    @Value("${producer.properties.retries_config}")
    private String retries_config;

    @Value("${producer.properties.value_serializer_class_config}")
    private String value_serializer_class_config;

    @Value("${producer.properties.key_serializer_class_config}")
    private String key_serializer_class_config;

    @Value("${producer.properties.topic}")
    private String topic;

    public TransferProducer(){

    }

    @PostConstruct
    public void init(){



    }

    public void sendTransfer(TransferDto transferDto) {

        Properties producerProperties = new Properties();

        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.20.4:9092");
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 0);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "serializer.TransferSerializer");
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, TransferDto> producer = new KafkaProducer<>(producerProperties)) {
            producer.send(new ProducerRecord<String, TransferDto>("transfers", transferDto));
            logger.info("Transfer " + transferDto.toString() + " submitted");
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
        TransferProducer producer = new TransferProducer();
        //producer.sendTransfer(test);
    }

}
