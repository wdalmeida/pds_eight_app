package eight.ing3.esipe.fr;

import eight.ing3.esipe.fr.entities.Transaction;
import eight.ing3.esipe.fr.services.NotificationService;
import entity.AccounEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.AccounRepository;
import repository.NotificationRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.lang.String.valueOf;
import static java.time.Instant.now;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {


    @InjectMocks
    NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private AccounRepository accounRepository;

    @Test
    public void shouldCreateNotification(){
        Transaction transaction = new Transaction();
        AccounEntity accounEntity = new AccounEntity();
        accounEntity.setBalance(100);

        //when(accounRepository.findOne(transaction.getIbansender())).thenReturn(accounEntity);
        transaction.setMontant(1000.00);
        transaction.setDate(new Date(01/01/2018));
        transaction.setDetail("détail");
        transaction.setIbansender("FR00000");
        transaction.setIbanrecipient("FR11111");
        transaction.setIntitule("intitulé");

        boolean created = notificationService.createNotification(transaction);
        assertEquals(true, created);
    }
}