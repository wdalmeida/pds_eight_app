package fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import fr.service.MockProductionTransaction;
import twitter4j.TwitterException;

import javax.annotation.PostConstruct;
import java.io.IOException;


@EnableJpaRepositories("fr.repository")
@EntityScan("fr.entity")
@SpringBootApplication
@EnableScheduling
@Configuration
@EnableAutoConfiguration
public class Application {

    @Autowired
    MockProductionTransaction m;

    @PostConstruct
    private void testService () throws IOException, TwitterException {
       // m.scheduleFixedRateTask();
       // ArrayList<String> results = IntegrateTwitterData.getTweets("#world");
        //System.out.println(results+"\n");
        //System.out.println(results.size());
       // IntegrateTweets.getTweets("#world");
    }
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}