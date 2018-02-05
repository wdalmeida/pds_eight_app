import dto.TransferDto;
import entity.AccounEntity;
import entity.TransactionEntity;
import entity.TransferEntity;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.AccounRepository;
import repository.TransactionRepository;
import repository.TransferRepository;
import service.TransferService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

    @InjectMocks
    TransferService transferService;

    @Mock
    AccounRepository accounRepository;

    @Mock
    TransferRepository transferRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void shouldCreateTransferAndTransaction(){

        TransferDto transferDto = new TransferDto();
        transferDto.setAmount(1);
        transferDto.setBeneficiaryIban("FR897987");

        AccounEntity accounEntity = new AccounEntity();
        accounEntity.setBalance(100);

        when(accounRepository.findOne(transferDto.getSendingIBAN())).thenReturn(accounEntity);
        boolean saved = transferService.createTransferAndTransaction(transferDto);

        assertEquals(true,saved);
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
        verify(transferRepository, times(1)).save(any(TransferEntity.class));
        verify(accounRepository, times(1)).save(any(AccounEntity.class));
    }

    @Test
    public void shouldNotCreateTransferAndTransaction() {
        TransferDto transferDto = new TransferDto();
        transferDto.setAmount(100);
        transferDto.setBeneficiaryIban("FR897987");

        AccounEntity accounEntity = new AccounEntity();
        accounEntity.setBalance(10);

        when(accounRepository.findOne(transferDto.getSendingIBAN())).thenReturn(accounEntity);
        boolean saved = transferService.createTransferAndTransaction(transferDto);

        assertEquals(false,saved);
        verify(transactionRepository, times(0)).save(any(TransactionEntity.class));
        verify(transferRepository, times(0)).save(any(TransferEntity.class));
        verify(accounRepository, times(0)).save(any(AccounEntity.class));

    }

}
