package eight.ing3.esipe.fr.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component(value = "provider_a")
public class MockMarketStockProviderA implements IMarketStockProvider {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProviderA.class);

    //default parameters
    private String codeCompany = "OR";
    private String srcCurrency = "USD";
    private String targetCurrency = "EUR";

    @Value("${mock.stock_market.method}")
    private String method;

    @Value("${mock.stock_market.url}")
    private String url;

    @Value("${mock.stock_market.param.company_code}")
    private String paramCompanyCode;

    @Value("${mock.stock_market.param.src_currency}")
    private String paramSrcCurrency;

    @Value("${mock.stock_market.param.target_currency}")
    private String paramTargetCurrency;

    private String response;

    /**
     * Build the request
     * @return
     */
    @Override
    public String getUrlRequest() {
        return this.url +
                this.paramCompanyCode + "/" + codeCompany +
                "/" + this.paramSrcCurrency + "/" + srcCurrency +
                "/" + this.paramTargetCurrency + "/" + targetCurrency;
    }

    @Override
    public String getCodeCompany() {
        return codeCompany;
    }

    @Override
    public String getSrcCurrency() {
        return srcCurrency;
    }

    @Override
    public String getTargetCurrency() {
        return targetCurrency;
    }

    @Override
    public void setCodeCompany(String codeCompany) {
        this.codeCompany = codeCompany;
    }

    @Override
    public void setSrcCurrency(String srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    @Override
    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String handlingResponse(String urlRequest) throws IOException {

        logger.info("url request : " + urlRequest);

        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.getForObject(urlRequest, String.class);

        //Mapper is configured to ignore unknowned parameters
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //Map the json string into dto object
        List<DTOProvidorA> readValue = mapper.readValue(response, new TypeReference<List<DTOProvidorA>>(){});

        logger.info("Amount of dto object created form parsin json string : " + readValue.size());

        return response;
    }

    @Override
    public boolean valideCode(String companyCode) {

        if (companyCode.equals("OR") || companyCode.equals("GLE") || companyCode.equals("CA")) {
            return true;
        }
        return false;

    }
}
