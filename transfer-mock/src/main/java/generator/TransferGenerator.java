package generator;

import org.springframework.stereotype.Component;
import producer.TransferProducer;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Component
public class TransferGenerator {

    @PostConstruct
    public void generate() throws InterruptedException {

        TransferProducer transferProducer = new TransferProducer();
        transferProducer.init();

        Random r = new Random();
        ArrayList list = new ArrayList();
        Date date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int percent;
        String text;
        while(true) {
            //amount
            text = String.format(Locale.ROOT, "%.2f", 1 + r.nextFloat() * (700 - 1));
            text = text + "; " + dateFormat.format(date);
            text = text + "; ";
            percent = r.nextInt(101);
            if (percent < 25) {
                text = text + "payment card";
            } else if (percent >= 20 && percent < 40) {
                text = text + "transaction";
            } else if (percent >= 40 && percent < 60) {
                text = text + "withdrawal cash";
            } else if (percent >= 60 && percent < 80) {
                text = text + "direct debit";
            } else if (percent >= 80 && percent < 101) {
                text = text + "payfree";
            }
            text = text + "; FR" + r.nextInt((1000000000 - 0100000000) + 1) + 1 + "000" + r.nextInt((1000000000 - 0100000000) + 1) + 1;
            //balance before transaction
            text = text + "; " + String.format(Locale.ROOT, "%.2f", 1 + r.nextFloat() * (2000 - 1));
            transferProducer.sendTransfer(text);

            final BlockingDeque<Boolean> dq = new LinkedBlockingDeque<>(1);
            dq.pollFirst(250, TimeUnit.MILLISECONDS);
        }
    }
}
