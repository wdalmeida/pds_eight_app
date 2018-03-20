import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"generator"})
public class TransferMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferMockApplication.class, args);
    }

}