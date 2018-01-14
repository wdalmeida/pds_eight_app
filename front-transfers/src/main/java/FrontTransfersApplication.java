import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"controller"})
public class FrontTransfersApplication {

    public static void main(String[] args){
        SpringApplication.run(FrontTransfersApplication.class, args);
    }

}