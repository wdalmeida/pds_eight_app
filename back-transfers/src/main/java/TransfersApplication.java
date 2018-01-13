import consumer.TransferConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"consumer"})
public class TransfersApplication {

    public static void main(String[] args){
        SpringApplication.run(TransfersApplication.class, args);
    }

}
