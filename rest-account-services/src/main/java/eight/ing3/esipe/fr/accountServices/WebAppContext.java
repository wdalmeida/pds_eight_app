package eight.ing3.esipe.fr.accountServices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "eight.ing3.esipe.fr.accountServices.accountServices.controllers",
        "eight.ing3.esipe.fr.accountServices.authentication.controller"
})
public class WebAppContext extends WebMvcConfigurerAdapter {

   


}
