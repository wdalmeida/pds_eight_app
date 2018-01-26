package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.provider.MockMarketStockProvider;
import eight.ing3.esipe.fr.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vyach on 18/01/2018.
 */
@RestController
public class GetStockMarketController {

    private final Logger logger = LoggerFactory.getLogger(GetStockMarketController.class);

    @Autowired
    private Properties properties;

    @Autowired
    private MockMarketStockProvider marketStockProvider;

    @RequestMapping("/")
    public String index() {

        logger.info("Mock Stock Market URL : " + properties.getStockMarketUrl());

        return "The gateway is running !";
    }

    @RequestMapping(value = "/company/{codeCompany}/src/{srcCurrency}/target/{targetCurrency}", method = RequestMethod.GET)
    public String getStockMarketFlow(
            @PathVariable String codeCompany,
            @PathVariable String srcCurrency,
            @PathVariable String targetCurrency
    ) {

        //build the provider
        marketStockProvider.setCodeCompany(codeCompany);
        marketStockProvider.setSrcCurrency(srcCurrency);
        marketStockProvider.setTargetCurrency(targetCurrency);

        String urlRequest = marketStockProvider.getUrlRequest();



        logger.info("URL request : " + urlRequest);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(urlRequest, String.class);


        return response.toString();
    }
}
