package eight.ing3.esipe.fr.provider;

/**
 * Created by Vyach on 18/01/2018.
 */
public interface IMarketStockProvider {

    public String getUrlRequest();
    public String getCodeCompany();
    public String getSrcCurrency();
    public String getTargetCurrency();
    public void setCodeCompany(String codeCompany);
    public void setSrcCurrency(String srcCurrency);
    public void setTargetCurrency(String targetCurrency);

    public String handlingResponse(String urlRequest);
}
