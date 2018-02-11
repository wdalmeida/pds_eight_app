package eight.ing3.esipe.fr.provider;

import eight.ing3.esipe.fr.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vyach on 11/02/2018.
 */
@Component(value = "provider_b")
public class MockMarketStockProviderB implements IMarketStockProvider {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProviderB.class);

    @Autowired
    private Properties properties;

    private String response;

    @Override
    public String getUrlRequest() {

        String urlRequest = this.properties.getStockMarketUrl()
                + this.properties.getMockStockMarketMethod();

        return urlRequest;
    }

    @Override
    public String getCodeCompany() {
        return null;
    }

    @Override
    public String getSrcCurrency() {
        return null;
    }

    @Override
    public String getTargetCurrency() {
        return null;
    }

    @Override
    public void setCodeCompany(String codeCompany) {

    }

    @Override
    public void setSrcCurrency(String srcCurrency) {

    }

    @Override
    public void setTargetCurrency(String targetCurrency) {

    }

    @Override
    public String handlingResponse(String urlRequest) {

        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.getForObject(urlRequest, String.class);

        return response;
    }
}
