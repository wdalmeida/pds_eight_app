package eight.ing3.esipe.fr.provider;

import eight.ing3.esipe.fr.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vyach on 18/01/2018.
 */
@Component(value = "provider_a")
public class MockMarketStockProviderA implements IMarketStockProvider {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProviderA.class);

    @Autowired
    private Properties properties;

    //default parameters
    private String codeCompany = "OR";
    private String srcCurrency = "USD";
    private String targetCurrency = "EUR";

    /**
     * Build the request
     * @return
     */
    @Override
    public String getUrlRequest() {
        return this.properties.getStockMarketUrl() +
                this.properties.getMockStockMarketCompanyCode() + "/" + codeCompany +
                "/" + this.properties.getMockStockMarketSrcCurrency() + "/" + srcCurrency +
                "/" + this.properties.getMockStockMarketTargetCurrency() + "/" + targetCurrency;
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

}
