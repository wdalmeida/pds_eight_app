package eight.ing3.esipe.fr.accountServices;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import eight.ing3.esipe.fr.accountServices.authentication.filter.AuthenticationFilter;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("repository")
@EntityScan("entity")
@EnableCaching
@SpringBootApplication
public class RestAccountServicesApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthenticationFilter());
		registrationBean.addUrlPatterns("/account/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestAccountServicesApplication.class, args);
	}
}
