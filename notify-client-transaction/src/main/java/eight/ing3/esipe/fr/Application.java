package eight.ing3.esipe.fr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.esipe.fr.entities.Transaction;
import eight.ing3.esipe.fr.kafkaServices.producer.Sender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@Configuration
@EnableAutoConfiguration
public class Application implements ApplicationListener<ApplicationReadyEvent> {

    private final static Logger logger = Logger.getLogger(Application.class);
    @Autowired
    private XmlMapper xmlMapper;

    @Autowired
    private Sender sender;

    @Value("${kafka.topic.transactionQueue}")
    private String topicName;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed hfhzefrate task - " + System.currentTimeMillis() / 1000);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Date(System.currentTimeMillis())));
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(transactions);
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
        }

        this.sender.send(topicName, xml);
    }
}
