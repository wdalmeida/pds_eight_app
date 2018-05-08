import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"listener"})
public class TwitterProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterProducerApplication.class, args);
    }

}