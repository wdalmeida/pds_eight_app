package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vyach on 18/01/2018.
 */
@RestController
public class GetStockMarket {

    @Autowired
    private Properties properties;

    @RequestMapping("/")
    public String index() {

        System.out.println(this.properties.getStockMarketUrl());

        return "The gateway is running !";
    }
}
