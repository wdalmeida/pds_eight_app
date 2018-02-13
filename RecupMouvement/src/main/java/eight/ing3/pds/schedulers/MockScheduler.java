
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void check(){

    }


    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {

        try {
            logger.info("sending random data");

            List<Transaction> transactions = new ArrayList<>();
            Transaction transaction = new Transaction();
            Random r = new Random();
            int randomNumber = r.nextInt(999999) + 100000;
            LocalDate date  = LocalDate.now();
            float min = 5.00F;
            float max = 2000.00F;
            double amount = min + Math.random() * (max - min);
            String wording = "wording" + randomNumber;
            String ibanSender = "FR00000" + randomNumber + "00000000000000";
            transaction.setDate(new Timestamp(System.currentTimeMillis()));
            transaction.setMontant(amount);
            transaction.setIntitule(wording);
            transaction.setIbansender(ibanSender);


            transactions.add(transaction);
            String xml = xmlMapper.writeValueAsString(transactions);
            this.sender.send(topicName, xml);
        }catch ( Exception ex) {
            logger.fatal("An error was thrown " + ex.getMessage());
        }
    }
}

