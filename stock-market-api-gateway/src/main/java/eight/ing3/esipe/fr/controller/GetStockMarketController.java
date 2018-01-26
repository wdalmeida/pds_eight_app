package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.provider.IMarketStockProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vyach on 18/01/2018.
 */
@RestController
public class GetStockMarketController {

    private final Logger logger = LoggerFactory.getLogger(GetStockMarketController.class);

    @Autowired
    @Qualifier("provider_a")
    private IMarketStockProvider marketStockProviderA;

    @RequestMapping("/")
    public String index() {


        return "The gateway is running !";
    }

    @RequestMapping(value = "/company/{codeCompany}/src/{srcCurrency}/target/{targetCurrency}", method = RequestMethod.GET)
    public String getStockMarketFlow(
            @PathVariable String codeCompany,
            @PathVariable String srcCurrency,
            @PathVariable String targetCurrency
    ) {

        //build the provider
        marketStockProviderA.setCodeCompany(codeCompany);
        marketStockProviderA.setSrcCurrency(srcCurrency);
        marketStockProviderA.setTargetCurrency(targetCurrency);

        String urlRequest = marketStockProviderA.getUrlRequest();



        logger.info("URL request : " + urlRequest);

        String response = marketStockProviderA.handlingResponse(urlRequest);


        return response;
    }
}
