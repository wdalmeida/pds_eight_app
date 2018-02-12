package eight.ing3.esipe.fr.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Vyach on 12/02/2018.
 */

public class GatewayTest {


    /**
     * Test the valid input for providor A
     */
    /*@Test
    public void getResponseFromProvidorAValidCompanyCodeTest() throws IOException {

        //providor A need companyCode, srcCurrency, targetCurrency
        //companyCode allowed : OR, GLE, CA
        //srcCurrency allowed : USD
        //targetCurrency allowed : EUR


        Gateway gateway = new Gateway();

        String response1 = gateway.getResponseFromProvider("OR", "USD", "EUR");
        String response2 = gateway.getResponseFromProvider("GLE", "USD", "EUR");
        String response3 = gateway.getResponseFromProvider("CA", "USD", "EUR");

        assertFalse(response1 != null);
        assertFalse(response2 != null);
        assertFalse(response3 != null);

    }*/
}
