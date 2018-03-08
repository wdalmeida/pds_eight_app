package eight.ing3.esipe.fr.service.providor;

import eight.ing3.esipe.fr.provider.MockMarketStockProviderA;
import eight.ing3.esipe.fr.provider.dto.DTOProvidorA;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vyach on 12/02/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
        "mock.stock_market.url=test.com",
})
public class MockMarketStockProvidorATest {

    private final Logger logger = LoggerFactory.getLogger(MockMarketStockProvidorATest.class);


    private MockMarketStockProviderA providor;

    @Value("${mock.stock_market.url}")
    private String url;

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
     *
     * Integration Test
     */
    @Test
    public void testHandlingResponse() throws IOException {

        logger.info("URL : " + url);

        if (!url.equals("test.com")) {
            List<DTOProvidorA> response = providor.handlingResponse(this.url);

            assertTrue(response.size() > 0);

        }

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
