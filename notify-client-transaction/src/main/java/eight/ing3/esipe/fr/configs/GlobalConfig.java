package eight.ing3.esipe.fr.configs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public XmlMapper getXmlMapper() {
        return new XmlMapper();
    }

}
