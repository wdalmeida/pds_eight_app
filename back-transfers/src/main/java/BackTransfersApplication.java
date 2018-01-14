import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"consumer"})
public class BackTransfersApplication {

    public static void main(String[] args){
        SpringApplication.run(BackTransfersApplication.class, args);
    }

}
