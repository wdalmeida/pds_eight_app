package eight.ing3.esipe.fr;

import eight.ing3.esipe.fr.entities.Transaction;
import eight.ing3.esipe.fr.services.NotificationService;
import entity.AccounEntity;
import entity.NotificationEntity;
import java.sql.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import repository.AccounRepository;
import repository.NotificationRepository;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

 
    @InjectMocks
    NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private AccounRepository accounRepository;

    @Before
    public void before() {
        this.notificationService.setAccounRepository(accounRepository);
        this.notificationService.setNotificationRepository(notificationRepository);
    }
    @Test
    public void shouldCreateNotification(){
        Transaction transaction = new Transaction();
        AccounEntity accounEntity = new AccounEntity();
        accounEntity.setBalance(100);

        transaction.setMontant(1000.00);
        transaction.setDate(new Date(01/01/2018));
        transaction.setDetail("détail");
        transaction.setIbansender("FR00000");
        transaction.setIbanrecipient("FR11111");
        transaction.setIntitule("intitulé");

        boolean created = notificationService.createNotification(transaction);
        assertEquals(true, created);
        Mockito.verify(this.accounRepository, Mockito.times(1)).findOne(Matchers.any(String.class));
        Mockito.verify(this.notificationRepository, Mockito.times(1)).save(Matchers.any(NotificationEntity.class));
    }
    
}