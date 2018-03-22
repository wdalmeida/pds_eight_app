import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"consumer"})
public class TransferDataManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(TransferDataManagerApplication.class, args);
    }

}