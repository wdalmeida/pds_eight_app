package eight.ing3.esipe.fr.stockmarketclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockMarketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketClientApplication.class, args);
	}
}
