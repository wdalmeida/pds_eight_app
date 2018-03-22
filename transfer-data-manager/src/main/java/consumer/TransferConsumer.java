package consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TransferConsumer {

    private static Logger logger = Logger.getLogger(TransferConsumer.class);

    private Properties consumerProperties;

    @Value("${consumer.properties.bootstrap_servers_config}")
    private String bootstrap_servers_config;

    @Value("${consumer.properties.group_id_config}")
    private String group_id_config;

    @Value("${consumer.properties.auto_offset_reset_config}")
    private String auto_offset_reset_config;

    @Value("${consumer.properties.value_deserializer_class_config}")
    private String value_deserializer_class_config;

    @Value("${consumer.properties.key_deserializer_class_config}")
    private String key_deserializer_class_config;

    @Value("${consumer.properties.topic}")
    private String topic;

    private AtomicBoolean stopConsumingThread = new AtomicBoolean(false);

    private AtomicBoolean stopped = new AtomicBoolean(false);

    public TransferConsumer(){

    }

    @PostConstruct
    public void init() {
        consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, group_id_config);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset_config);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value_deserializer_class_config);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key_deserializer_class_config);
        consumeTransfer();
    }

    public void consumeTransfer() {

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties)) {
            consumer.subscribe(Collections.singletonList(topic));
            while (!stopConsumingThread.get()) {
                ConsumerRecords<String, String> messages = consumer.poll(100);
                for (ConsumerRecord<String, String> message : messages) {
                    logger.info("transfer received " + message.value());
                }
            }
            stopped.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        stopConsumingThread.set(true);
    }

    public boolean isStopped() {
        return stopped.get();
    }

}
