package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.provider.IMarketStockProvider;
import eight.ing3.esipe.fr.service.Gateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Vyach on 18/01/2018.
 */
@RestController
public class GetStockMarketController {

    private final Logger logger = LoggerFactory.getLogger(GetStockMarketController.class);


    @Autowired
    private Gateway gateway;

    @RequestMapping("/")
    public String index() {


        return "The gateway is running !";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/company/{codeCompany}/src/{srcCurrency}/target/{targetCurrency}", method = RequestMethod.GET)
    public String getStockMarketFlow(
            @PathVariable String codeCompany,
            @PathVariable String srcCurrency,
            @PathVariable String targetCurrency
    ) throws IOException {

        String response = gateway.getResponseFromProvider(codeCompany, srcCurrency, targetCurrency);


        return response;
    }
}
