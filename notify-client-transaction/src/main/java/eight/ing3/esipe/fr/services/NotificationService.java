package eight.ing3.esipe.fr.services;

import eight.ing3.esipe.fr.entities.Transaction;
import entity.AccounEntity;
import entity.NotificationEntity;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repository.AccounRepository;
import repository.NotificationRepository;

@Service("notificationService")
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AccounRepository accounRepository;

    @Value("${kafka.topic.transactionQueue}")
    private String topicName;

    @PostConstruct
    private void testNotificationRepository() {

        System.out.println(this.notificationRepository + " test");
    }

    @Transactional
    public boolean createNotification(Transaction transaction) {
        AccounEntity accounEntity = accounRepository.findOne(transaction.getIbansender());
        //Notification notification = new Notification();
        //notification.setIdNotification(id_notification);

        NotificationEntity notif = NotificationEntity.builder()
                .amount(transaction.getMontant())
                .date(new Timestamp(transaction.getDate().getTime()))
                .label(transaction.getIntitule())
                .detail(null)
                .status(false)
                .iban(accounEntity == null ? null : accounEntity.getIban())
                .build();

        notificationRepository.save(notif);
        return true;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void setAccounRepository(AccounRepository accounRepository) {
        this.accounRepository = accounRepository;
    }
    
    
}
