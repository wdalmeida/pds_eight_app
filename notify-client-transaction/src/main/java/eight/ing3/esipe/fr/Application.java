package eight.ing3.esipe.fr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableScheduling
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("repository")
@EntityScan("entity")
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
