package eight.ing3.esipe.fr.provider;

import eight.ing3.esipe.fr.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vyach on 18/01/2018.
 */
@Component
public class MockMarketStockProvider implements IMarketStockProvider {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProvider.class);

    @Autowired
    private Properties properties;

    private String codeCompany = "OR";
    private String srcCurrency = "USD";
    private String targetCurrency = "EUR";

    @Override
    public String getUrlRequest() {

        logger.info(this.properties.getStockMarketUrl());

        return this.properties.getStockMarketUrl() +
                "companies/" + codeCompany +
                "/fromcurrency/" + srcCurrency +
                "/tocurrency/" + targetCurrency;
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
