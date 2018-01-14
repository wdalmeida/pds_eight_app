package parser;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLParser {

    public static byte[] convertToSCTFormat(String sendingIBAN, double amount, String beneficiaryIban, LocalDate valueDate, String wording) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Document");
            rootElement.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.02");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(rootElement);

            Element pain = doc.createElement("pain.001.001.02");
            rootElement.appendChild(pain);

            Element grpHdr = doc.createElement("GrpHdr");
            pain.appendChild(grpHdr);

            Element msgId = doc.createElement("MsgId");
            msgId.appendChild(doc.createTextNode("ABC/060928/CCT001"));
            grpHdr.appendChild(msgId);

            Element creDtTm = doc.createElement("CreDtTm");
            creDtTm.appendChild(doc.createTextNode("2006-09-28T14:07:00"));
            grpHdr.appendChild(creDtTm);

            Element nbOfTxs = doc.createElement("NbOfTxs");
            nbOfTxs.appendChild(doc.createTextNode("1"));
            grpHdr.appendChild(nbOfTxs);

            Element ctrlSum = doc.createElement("CtrlSum");
            ctrlSum.appendChild(doc.createTextNode(String.valueOf(amount)));
            grpHdr.appendChild(ctrlSum);

            Element grpg = doc.createElement("Grpg");
            grpg.appendChild(doc.createTextNode("GRPD"));
            grpHdr.appendChild(grpg);

            Element initgPty = doc.createElement("InitgPty");
            grpHdr.appendChild(initgPty);

            Element nm = doc.createElement("Nm");
            nm.appendChild(doc.createTextNode("BEM"));
            initgPty.appendChild(nm);

            Element pmtInf = doc.createElement("PmtInf");
            pain.appendChild(pmtInf);

            Element pmtMtd = doc.createElement("PmtMtd");
            pmtMtd.appendChild(doc.createTextNode("TRF"));
            pmtInf.appendChild(pmtMtd);

            Element reqdExctnDt = doc.createElement("ReqdExctnDt");
            reqdExctnDt.appendChild(doc.createTextNode(String.valueOf(valueDate)));
            pmtInf.appendChild(reqdExctnDt);

            Element dbtr = doc.createElement("Dbtr");
            pmtInf.appendChild(dbtr);

            Element nm2 = doc.createElement("Nm");
            nm2.appendChild(doc.createTextNode("ABC Corporation"));
            dbtr.appendChild(nm2);

            Element dbtrAcct = doc.createElement("DbtrAcct");
            pmtInf.appendChild(dbtrAcct);

            Element id = doc.createElement("Id");
            dbtrAcct.appendChild(id);

            Element iban = doc.createElement("IBAN");
            iban.appendChild(doc.createTextNode(sendingIBAN));
            id.appendChild(iban);

            Element dbtrAgt = doc.createElement("DbtrAgt");
            pmtInf.appendChild(dbtrAgt);

            Element finInstnId = doc.createElement("FinInstnId");
            dbtrAgt.appendChild(finInstnId);

            Element bic = doc.createElement("BIC");
            bic.appendChild(doc.createTextNode("BEMBBXXX"));
            finInstnId.appendChild(bic);

            Element cdtTrfTxInf = doc.createElement("CdtTrfTxInf");
            pmtInf.appendChild(cdtTrfTxInf);

            Element pmtId = doc.createElement("PmtId");
            cdtTrfTxInf.appendChild(pmtId);

            Element endToEndId = doc.createElement("EndToEndId");
            endToEndId.appendChild(doc.createTextNode(wording));
            pmtId.appendChild(endToEndId);

            Element amt = doc.createElement("Amt");
            cdtTrfTxInf.appendChild(amt);

            Element instdAmt = doc.createElement("InstdAmt");
            instdAmt.appendChild(doc.createTextNode("10.00"));
            instdAmt.setAttribute("Ccy", "EUR");
            amt.appendChild(instdAmt);

            Element cdtr = doc.createElement("Cdtr");
            cdtTrfTxInf.appendChild(cdtr);

            Element nm3 = doc.createElement("Nm");
            nm3.appendChild(doc.createTextNode("BACcorp"));
            cdtr.appendChild(nm3);

            Element cdtrAcct = doc.createElement("CdtrAcct");
            cdtTrfTxInf.appendChild(cdtrAcct);

            Element id2 = doc.createElement("Id");
            cdtrAcct.appendChild(id2);

            Element iban2 = doc.createElement("IBAN");
            iban2.appendChild(doc.createTextNode(beneficiaryIban));
            id2.appendChild(iban2);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(bos);
            transformer.transform(source, result);

            return bos.toByteArray();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return null;
        }
    }
}