package eight.ing3.esipe.fr.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Vyach on 18/01/2018.
 */
@Component
public class Properties {

    @Value("${mock.stock_market.url}")
    private String stockMarketUrl;

    public String getStockMarketUrl() {
        return stockMarketUrl;
    }

    public void setStockMarketUrl(String stockMarketUrl) {
        this.stockMarketUrl = stockMarketUrl;
    }
}
