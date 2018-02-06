package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;
import service.FraudulentTransferLearning;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class FraudulentTransferController {

    @Autowired
    FraudulentTransferLearning fraudulentTransferLearning;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void test() throws JAXBException, IOException, SAXException {
        fraudulentTransferLearning.testPmml();

    }
}
