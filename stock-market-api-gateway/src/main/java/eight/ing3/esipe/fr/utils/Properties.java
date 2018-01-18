package eight.ing3.esipe.fr.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Vyach on 18/01/2018.
 */
@Component
public class Properties {

    @Value("${mock.stock_market.url}")
    private String mockStockMarketUrl;

    @Value("${mock.stock_market.method}")
    private String mockStockMarketMethod;

    @Value("${mock.stock_market.param.company_code}")
    private String mockStockMarketCompanyCode;

    @Value("${mock.stock_market.param.src_currency}")
    private String mockStockMarketSrcCurrency;

    @Value("${mock.stock_market.param.target_currency}")
    private String mockStockMarketTargetCurrency;

    public String getMockStockMarketMethod() {
        return mockStockMarketMethod;
    }

    public void setMockStockMarketMethod(String mockStockMarketMethod) {
        this.mockStockMarketMethod = mockStockMarketMethod;
    }

    public String getMockStockMarketCompanyCode() {
        return mockStockMarketCompanyCode;
    }

    public void setMockStockMarketCompanyCode(String mockStockMarketCompanyCode) {
        this.mockStockMarketCompanyCode = mockStockMarketCompanyCode;
    }

    public String getMockStockMarketSrcCurrency() {
        return mockStockMarketSrcCurrency;
    }

    public void setMockStockMarketSrcCurrency(String mockStockMarketSrcCurrency) {
        this.mockStockMarketSrcCurrency = mockStockMarketSrcCurrency;
    }

    public String getMockStockMarketTargetCurrency() {
        return mockStockMarketTargetCurrency;
    }

    public void setMockStockMarketTargetCurrency(String mockStockMarketTargetCurrency) {
        this.mockStockMarketTargetCurrency = mockStockMarketTargetCurrency;
    }


    public String getStockMarketUrl() {
        return mockStockMarketUrl;
    }

    public void setStockMarketUrl(String mockStockMarketUrl) {
        this.mockStockMarketUrl = mockStockMarketUrl;
    }
}
