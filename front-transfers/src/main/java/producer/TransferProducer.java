package producer;

import model.TransferModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static java.time.LocalDate.now;

public class TransferProducer {

    public static void sendTransfer(TransferModel transfer) {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "serializer.TransferSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, TransferModel> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<String, TransferModel>("transfers", transfer));
            System.out.println("Transfer " + transfer.toString() + " submitted");
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
        TransferProducer.sendTransfer(test);
    }

}
