package eight.ing3.esipe.fr.stockmarketclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vyach on 15/02/2018.
 */
@Controller
public class StockMarketController {

    private final Logger logger = LoggerFactory.getLogger(StockMarketController.class);

    @Value("${server.backend}")
    private String host;

    @RequestMapping("/euronext")
    public String euroNextSM(Model model) {

        model.addAttribute("name", "test");

        return "euronext";
    }

}
