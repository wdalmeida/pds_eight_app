package eight.ing3.esipe.fr.controller;

import eight.ing3.esipe.fr.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vyach on 18/01/2018.
 */
@RestController
public class GetStockMarketController {

    private final Logger logger = LoggerFactory.getLogger(GetStockMarketController.class);

    @Autowired
    private Properties properties;

    @RequestMapping("/")
    public String index() {

        logger.info("Mock Stock Market URL : " + properties.getStockMarketUrl());

        return "The gateway is running !";
    }
}
