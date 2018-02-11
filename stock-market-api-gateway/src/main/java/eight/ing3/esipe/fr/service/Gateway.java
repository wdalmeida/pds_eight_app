package eight.ing3.esipe.fr.service;

import eight.ing3.esipe.fr.provider.IMarketStockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Vyach on 11/02/2018.
 */

/**
 * Gateway that manage to get response from a stock market exchange api
 */
@Component
public class Gateway {

    @Autowired
    @Qualifier("provider_a")
    private IMarketStockProvider marketStockProviderA;

    @Autowired
    @Qualifier("provider_b")
    private IMarketStockProvider marketStockProviderB;


    public String getResponseFromProvider(String codeCompany, String srcCurrency, String targetCurrency) {

        String response = "{}";

        if (marketStockProviderA.valideCode(codeCompany)) {
            marketStockProviderA.setCodeCompany(codeCompany);
            marketStockProviderA.setSrcCurrency(srcCurrency);
            marketStockProviderA.setTargetCurrency(targetCurrency);

            response = marketStockProviderA.handlingResponse(marketStockProviderA.getUrlRequest());

        }
        else if (marketStockProviderB.valideCode(codeCompany)) {
            marketStockProviderB.setCodeCompany(codeCompany);

            response = marketStockProviderB.handlingResponse(marketStockProviderB.getUrlRequest());
        }



        return response;
    }

}
