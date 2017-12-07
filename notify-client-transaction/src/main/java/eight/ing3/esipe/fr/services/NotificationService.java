package eight.ing3.esipe.fr.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import eight.ing3.esipe.fr.entities.Notification;
import eight.ing3.esipe.fr.entities.Transaction;
import eight.ing3.esipe.fr.kafkaServices.producer.Sender;
import eight.ing3.esipe.fr.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("notificationRepository")
public class NotificationService {

    Notification notification = new Notification();

    @Autowired
    private XmlMapper xmlMapper;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private Sender sender;

    @Value("${kafka.topic.transactionQueue}")
    private String topicName;

    @PostConstruct
    private void testNotificationRepository(){
        System.out.println(this.notificationRepository + " test");
    }

    @Bean
    public XmlMapper getXmlMapper(){
        return new XmlMapper();
    }

    @Bean
    public void sendToQueue() throws JsonProcessingException {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, null));
        transactions.add(new Transaction("personne", "873879873", "intitul", 1000.00, "detail", "myIban", null, new Date(System.currentTimeMillis())));
        String xml = xmlMapper.writeValueAsString(transactions);

        this.sender.send(topicName, xml);
    }

    @RequestMapping("notification")
    public void createNotification(Double amount, Timestamp date, String details,
                                   Integer idAccount, String label, String recipient, String status, String typeTransaction) {
       //Notification notification = new Notification();
       //notification.setIdNotification(id_notification);
       notification.setAmount(amount);
       notification.setDate(date);
       notification.setDetails(details);
       notification.setIdAccount(idAccount);
       notification.setLabel(label);
       notification.setRecipient(recipient);
       notification.setStatus(status);
       notification.setTypeTransac(typeTransaction);

       //notificationRepository.save(notification);
    }
}
