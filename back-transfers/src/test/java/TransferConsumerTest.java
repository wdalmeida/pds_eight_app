import consumer.TransferConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransferConsumerTest {

    @InjectMocks
    TransferConsumer transferConsumer;

    @Mock
    KafkaConsumer consumer;

    @Test
    public void consumerShouldStop() throws InterruptedException {

        /*transferConsumer = new TransferConsumer();
        final BlockingDeque<Boolean> dq = new LinkedBlockingDeque<>(1);
        dq.pollFirst(500, TimeUnit.MILLISECONDS);

        transferConsumer.stop();
        verify(consumer, atLeast(1)).poll(anyInt());
*/
    }

}
