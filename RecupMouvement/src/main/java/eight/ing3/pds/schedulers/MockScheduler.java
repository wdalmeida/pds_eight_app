package eight.ing3.pds.schedulers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.pds.entities.Transaction;
import eight.ing3.pds.kafkaServices.producer.Sender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class MockScheduler {

    private static final Logger logger = Logger.getLogger(MockScheduler.class);

    @Value("${kafka.topic.transactionQueue}")
    private String topicName;


    @Autowired
    private XmlMapper xmlMapper;


    @Autowired
    private Sender sender;

    @PostConstruct
    public void trototo(){

    }


    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {

        try {
            logger.info("sending random data");

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
            transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
            transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Date(System.currentTimeMillis())));
            String xml = xmlMapper.writeValueAsString(transactions);
            this.sender.send(topicName, xml);
        }catch ( Exception ex) {
            logger.fatal("An error was thrown " + ex.getMessage());
        }
    }

}
