package eight.ing3.esipe.fr.service.providor;

import eight.ing3.esipe.fr.provider.MockMarketStockProviderA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vyach on 12/02/2018.
 */
public class MockMarketStockProvidorATest {

    private MockMarketStockProviderA providor;

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
}
