import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"consumer"})
public class TwitterDataManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(TwitterDataManagerApplication.class, args);
    }

}