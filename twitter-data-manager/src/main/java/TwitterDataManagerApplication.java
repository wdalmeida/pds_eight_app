import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repositories.TweetRepository;

@SpringBootApplication(scanBasePackages={"repositories", "consumer"})
@EnableMongoRepositories(basePackageClasses = TweetRepository.class)
public class TwitterDataManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(TwitterDataManagerApplication.class, args);
    }

}