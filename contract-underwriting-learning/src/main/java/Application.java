import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.MockProductionTransaction;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("repository")
@EntityScan("entity")
@SpringBootApplication(scanBasePackages={"service"})
public class Application {

    @Autowired
    MockProductionTransaction m;

    @PostConstruct
    private void testService () throws IOException {
        m.scheduleFixedRateTask();
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }



}