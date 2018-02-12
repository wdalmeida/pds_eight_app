package eight.ing3.esipe.fr.provider;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vyach on 18/01/2018.
 */
public interface IMarketStockProvider<T> {

    public String getUrlRequest();
    public String getCodeCompany();
    public String getSrcCurrency();
    public String getTargetCurrency();
    public void setCodeCompany(String codeCompany);
    public void setSrcCurrency(String srcCurrency);
    public void setTargetCurrency(String targetCurrency);

    public List<T> handlingResponse(String urlRequest) throws IOException;

    public boolean valideCode(String companyCode);
}
