import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("repository")
@EntityScan("entity")
@SpringBootApplication(scanBasePackages={"controller","service"})
public class FraudulentTransferApp {

    public static void main(String[] args) {
        SpringApplication.run(FraudulentTransferApp.class, args);
    }
}

