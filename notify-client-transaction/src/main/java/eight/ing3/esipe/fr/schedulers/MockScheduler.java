package eight.ing3.esipe.fr.schedulers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MockScheduler {

    @Value("${kafka.topic.transactionQueue}")
    private String topicName;

    @PostConstruct
    public void check(){
        System.out.println("checked");
    }
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
        /*

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Date(System.currentTimeMillis())));
        String xml = xmlMapper.writeValueAsString(transactions);

        this.sender.send(topicName, xml);
         */
    }

}
