package consumer;

import dto.TransferDto;
import model.TransferModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import parser.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.apache.log4j.Logger;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

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

    @Value("${externalBank.url}")
    private String urlExternalBank;

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

        try (KafkaConsumer<String, TransferDto> consumer = new KafkaConsumer<>(consumerProperties)) {
            consumer.subscribe(Collections.singletonList(topic));
            while (!stopConsumingThread.get()) {
                ConsumerRecords<String, TransferDto> messages = consumer.poll(100);
                for (ConsumerRecord<String, TransferDto> message : messages) {
                    logger.info("Transfer received " + message.value().toString());
                    TransferSubmiter transferSubmiter = new TransferSubmiter(message.value());
                    new Thread(transferSubmiter).start();
                    logger.info("submitter launched with transfer received");
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

    protected class TransferSubmiter implements Runnable {

        final TransferDto transferDto;

        public TransferSubmiter(TransferDto transferDto) {
            this.transferDto = transferDto;
        }

        public void run() {

            logger.info("transfer to submit :" + transferDto.toString());

            String sendingIBAN = transferDto.getSendingIBAN();
            double amount = transferDto.getAmount();
            String beneficiaryIban = transferDto.getBeneficiaryIban();
            LocalDate valueDate = transferDto.getValueDate();
            String wording = transferDto.getWording();

            try {

                //url of externalBank api
                URL url = new URL(urlExternalBank);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/xml");
                OutputStream os = conn.getOutputStream();
                byte[] xmlFile = XMLParser.convertToSCTFormat(sendingIBAN, amount, beneficiaryIban, valueDate, wording);
                os.write(xmlFile);
                os.flush();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_GATEWAY) {
                    logger.warn("La connexion a echoue (erreur 502)");
                    throw new RuntimeException("Connection failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        logger.info(output);
                    }
                    logger.info("Virement correctement envoyé");

                }
                conn.disconnect();

            } catch (MalformedURLException e) {
                logger.warn("La requête a echoue");
            } catch (IOException e) {
                logger.warn("La requête a echoue");
            }
        }
    }
}
