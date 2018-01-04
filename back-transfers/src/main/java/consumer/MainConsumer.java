package consumer;

import model.TransferModel;
import parser.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.apache.log4j.Logger;


public class MainConsumer {

    private static Logger logger = Logger.getLogger(MainConsumer.class);

    public void consumer(TransferModel transferModel) {

        logger.info(transferModel.toString());

        String sendingIBAN = transferModel.getSendingIBAN();
        double amount = transferModel.getAmount();
        String beneficiaryIban = transferModel.getBeneficiaryIban();
        LocalDate valueDate = transferModel.getValueDate();
        String wording = transferModel.getWording();

        try {

            //url of externalBank api
            URL url = new URL("http://int.eight.inside.esiag.info:9191/externalBank/submit/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");
            OutputStream os = conn.getOutputStream();
            byte[] xmlFile = XMLParser.convertToSCTFormat(sendingIBAN,amount,beneficiaryIban,valueDate,wording);
            os.write(xmlFile);
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_GATEWAY) {
                logger.warn("La connexion a echoue (erreur 502)");
                throw new RuntimeException("Connection failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    logger.info(output);
                }
                logger.info("Virement correctement envoyé");

            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            logger.warn("La requête a echoue");
        } catch (IOException e) {
            logger.warn("La requête a echoue");
        }
    }
}
