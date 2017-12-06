package eight.ing3.esipe.fr.services;

import eight.ing3.esipe.fr.bean.DateTransaction;
import eight.ing3.esipe.fr.bean.Notification;
import eight.ing3.esipe.fr.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;

@Service("notificationRepository")
public class NotificationService {

    Notification notification;

    @Autowired
    private NotificationRepository notificationRepository;

    @PostConstruct
    public void test(){
        System.out.println(this.notificationRepository + " test");
    }

    @RequestMapping("notification")
    public void createNotification(int id_notification, Double amount, Date date, String details,
                                   Integer idAccount, String label, String recipient, String status, String typeTransaction) {
       Notification notification = new Notification();
       notification.setIdNotification(id_notification);
       notification.setAmount(amount);
       notification.setDate(date);
       notification.setDetails(details);
       notification.setIdAccount(idAccount);
       notification.setLabel(label);
       notification.setRecipient(recipient);
       notification.setStatus(status);
       notification.setTypeTransac(typeTransaction);

       notificationRepository.save(notification);

       ;
        /*
    @RequestMapping("/mode")
    public String showProducts(){
        Mode m = new Mode();
        m.setSeats(2);
        repository.save(m); //this is where the error getting from
        return "product";
    }
       * */



    }
}
