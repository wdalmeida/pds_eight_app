import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repositories.TransferRepository;
import repositories.TweetRepository;

@SpringBootApplication(scanBasePackages={"controller","repositories","service"})
@EnableMongoRepositories(basePackageClasses = {TransferRepository.class, TweetRepository.class})
public class BigDataManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(BigDataManagerApplication.class, args);
    }

}