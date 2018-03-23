import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repositories.TransferRepository;

@SpringBootApplication(scanBasePackages={"consumer","repositories"})
@EnableMongoRepositories(basePackageClasses = TransferRepository.class)
public class TransferDataManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(TransferDataManagerApplication.class, args);
    }

}