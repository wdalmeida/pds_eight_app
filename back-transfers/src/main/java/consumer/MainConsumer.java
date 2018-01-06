package consumer;

import model.TransferModel;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class MainConsumer {

    private static Logger logger = Logger.getLogger(MainConsumer.class);

    public static void consume(){

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8082");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "deserializer.TransferDeserializer");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, TransferModel> consumer = new KafkaConsumer<>(consumerProperties)) {
            consumer.subscribe(Collections.singletonList("Transfer"));
            while (true) {
                ConsumerRecords<String, TransferModel> messages = consumer.poll(100);
                for (ConsumerRecord<String, TransferModel> message : messages) {
                    System.out.println("Transfer received " + message.value().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void submit(TransferModel transferModel) {

        logger.info(transferModel.toString());

        String sendingIBAN = transferModel.getSendingIBAN();
        double amount = transferModel.getAmount();
        String beneficiaryIban = transferModel.getBeneficiaryIban();
        LocalDate valueDate = transferModel.getValueDate();
        String wording = transferModel.getWording();

        try {

            //url of externalBank api
            URL url = new URL("http://int.eight.inside.esiag.info:9191/externalBank/submit/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");
            OutputStream os = conn.getOutputStream();
            byte[] xmlFile = XMLParser.convertToSCTFormat(sendingIBAN,amount,beneficiaryIban,valueDate,wording);
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
