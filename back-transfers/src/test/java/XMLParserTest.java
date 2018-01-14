import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import parser.XMLParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import static org.junit.Assert.assertEquals;

public class XMLParserTest {

    private byte[] xmlFile;

    @Before
    public void setup() {

        String sendingIBAN = "FR123456789123456789";
        double amount = 10;
        String beneficiaryIBAN = "FR987654321987654321";
        LocalDate valueDate = LocalDate.of(2018,01,14);
        String wording = "Test";

        xmlFile = XMLParser.convertToSCTFormat(sendingIBAN, amount, beneficiaryIBAN, valueDate, wording);
    }

    @Test
    public void verifyXmlFileStructureAndValues() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlFile));

            doc.getDocumentElement().normalize();

            //assert valueDate
            String LocalDate = doc.getElementsByTagName("ReqdExctnDt").item(0).getTextContent();
            assertEquals(LocalDate, "2018-01-14");

            //assertAmount
            Double amount = Double.parseDouble(doc.getElementsByTagName("InstdAmt").item(0).getTextContent());
            assertEquals(amount,Double.valueOf(10.00));

            //assertWording
            String wording = doc.getElementsByTagName("EndToEndId").item(0).getTextContent();
            assertEquals(wording,"Test");

            //assertSendingIBAN
            NodeList nList = doc.getElementsByTagName("CdtrAcct");
            String beneIBAN = ((Element) nList.item(0)).getElementsByTagName("IBAN").item(0).getTextContent();
            assertEquals(beneIBAN,"FR987654321987654321");

            //assertBeneficiaryIban
            NodeList nList2 = doc.getElementsByTagName("DbtrAcct");
            String sendingIBAN = ((Element) nList2.item(0)).getElementsByTagName("IBAN").item(0).getTextContent();
            assertEquals(sendingIBAN,"FR123456789123456789");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
