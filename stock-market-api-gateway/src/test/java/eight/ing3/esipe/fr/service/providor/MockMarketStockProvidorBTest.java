package eight.ing3.esipe.fr.service.providor;


import eight.ing3.esipe.fr.provider.MockMarketStockProviderB;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorB;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vyach on 12/02/2018.
 */
public class MockMarketStockProvidorBTest {

    private MockMarketStockProviderB providor;

    private static final String URL = "http://192.168.4.204:9090/stockmarket-b/stockmarket/companycode/MC";

    @Before
    public void init() {
        providor = new MockMarketStockProviderB();
    }

    @Test
    public void testValideCode() {

        boolean testValide1 = providor.valideCode("MC");
        boolean testValide2 = providor.valideCode("KER");
        boolean testNotValid1 = providor.valideCode("");
        boolean testNotValid2 = providor.valideCode("sd");

        assertTrue(testValide1);
        assertTrue(testValide2);
        assertFalse(testNotValid1);
        assertFalse(testNotValid2);
    }

    /**
     * Test the good handling of a providor
     * @throws IOException
     */
    @Test
    public void testHandlingResponse() throws IOException {

        List<DTOProvidorB> response = providor.handlingResponse(URL);


        assertTrue(response.size() > 0);

    }

    /**
     * Test when the url is unaccessible
     * @throws IOException
     */
    @Test(expected = ResourceAccessException.class)
    public void testExceptionFromWrongURL() throws IOException {
        List<DTOProvidorB> responseNull = providor.handlingResponse("http://something.com");

    }
}
