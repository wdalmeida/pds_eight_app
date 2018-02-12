package eight.ing3.esipe.fr.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Vyach on 11/02/2018.
 */
@Component(value = "provider_b")
public class MockMarketStockProviderB implements IMarketStockProvider {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProviderB.class);

    private String response;

    private String companyCode;

    @Value("${mockB.stock_market.method}")
    private String method;

    @Value("${mockB.stock_market.url}")
    private String url;

    @Override
    public String getUrlRequest() {

        String urlRequest = this.url + this.method + "/" + companyCode;

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
        this.companyCode = codeCompany;
    }

    @Override
    public void setSrcCurrency(String srcCurrency) {

    }

    @Override
    public void setTargetCurrency(String targetCurrency) {

    }

    @Override
    public String handlingResponse(String urlRequest) throws IOException {

        logger.info("url request : " + urlRequest);

        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.getForObject(urlRequest, String.class);

        return response;
    }

    @Override
    public boolean valideCode(String companyCode) {
        if (companyCode.equals("MC") || companyCode.equals("KER")) {
            return true;
        }
        return false;
    }
}
