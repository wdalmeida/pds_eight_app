package eight.ing3.esipe.fr.service;

import eight.ing3.esipe.fr.provider.IMarketStockProvider;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorA;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

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


    public String getResponseFromProvider(String codeCompany, String srcCurrency, String targetCurrency) throws IOException {

        String response = "{}";

        if (marketStockProviderA.valideCode(codeCompany)) {
            marketStockProviderA.setCodeCompany(codeCompany);
            marketStockProviderA.setSrcCurrency(srcCurrency);
            marketStockProviderA.setTargetCurrency(targetCurrency);

            List<DTOProvidorA> listFlowA = marketStockProviderA.handlingResponse(marketStockProviderA.getUrlRequest());

        }
        else if (marketStockProviderB.valideCode(codeCompany)) {
            marketStockProviderB.setCodeCompany(codeCompany);

            List<DTOProvidorB> listFlowB = marketStockProviderB.handlingResponse(marketStockProviderB.getUrlRequest());
        }



        return response;
    }

}
