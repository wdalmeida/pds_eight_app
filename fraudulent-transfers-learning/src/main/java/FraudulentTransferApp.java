import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"controller","service"})
public class FraudulentTransferApp {

    public static void main(String[] args) {
        SpringApplication.run(FraudulentTransferApp.class, args);
    }
}

