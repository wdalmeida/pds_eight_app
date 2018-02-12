package eight.ing3.esipe.fr.service.providor;

import eight.ing3.esipe.fr.provider.MockMarketStockProviderA;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorA;
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
public class MockMarketStockProvidorATest {

    private MockMarketStockProviderA providor;

    private static final String URL
            = "http://int.eight.inside.esiag.info:9090/mock-market-flow-api/marketflow/companies/OR/fromcurrency/USD/tocurrency/EUR";

    @Before
    public void init() {
        providor = new MockMarketStockProviderA();
    }

    @Test
    public void testValideCode() {

        boolean testValide1 = providor.valideCode("OR");
        boolean testValide2 = providor.valideCode("GLE");
        boolean testValide3 = providor.valideCode("CA");
        boolean testNotValid1 = providor.valideCode("");
        boolean testNotValid2 = providor.valideCode("sd");

        assertTrue(testValide1);
        assertTrue(testValide2);
        assertTrue(testValide3);
        assertFalse(testNotValid1);
        assertFalse(testNotValid2);
    }

    /**
     * Test the good handling of a providor
     * @throws IOException
     */
    @Test
    public void testHandlingResponse() throws IOException {

        List<DTOProvidorA> response = providor.handlingResponse(URL);


        assertTrue(response.size() > 0);

    }

    /**
     * Test when the url is unaccessible
     * @throws IOException
     */
    @Test(expected = ResourceAccessException.class)
    public void testExceptionFromWrongURL() throws IOException {
        List<DTOProvidorA> responseNull = providor.handlingResponse("http://something.com");

    }
}
